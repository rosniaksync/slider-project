let btnNext = document.querySelector(".next");
let btnBack = document.querySelector(".back");

let container = document.querySelector(".container");
let list = document.querySelector(".container .list");
let thumb = document.querySelector(".container .thumb");

let login = document.querySelector(".login");
let toggle = document.querySelector(".toggle");

function toggleBtn() {
    login.classList.toggle('modo-cadastro');

    const title = document.querySelector('.title');
    const subtitle = document.querySelector('.subtitle');

    if (login.classList.contains('modo-cadastro')) {
        title.textContent = 'Cadastrar';
        subtitle.textContent = 'Faça o cadastro para poder entrar';
        toggle.textContent = 'Já tem conta? Entrar';
    } else {
        title.textContent = 'Login';
        subtitle.textContent = 'Acesse sua conta para continuar';
        toggle.textContent = 'Não tem conta? Cadastre-se';
    }
}

toggle.addEventListener('click', toggleBtn);

btnNext.onclick = () => moveItemsOnClick('next');
btnBack.onclick = () => moveItemsOnClick('back');

function moveItemsOnClick(type) {
    let listItems = document.querySelectorAll('.list .list-item');
    let thumbItems = document.querySelectorAll('.thumb .thumb-item');

    if (type === 'next') {
        list.appendChild(listItems[0]);
        thumb.appendChild(thumbItems[0]);
        container.classList.add('next');
    } else if (type === 'back') {
        list.prepend(listItems[listItems.length - 1]);
        thumb.prepend(thumbItems[listItems.length - 1]);
        container.classList.add('back');
    }

    setTimeout(() => {
        container.classList.remove('next');
        container.classList.remove('back');
    }, 1000);
}

const form = document.querySelector('form');

form.addEventListener('submit', async (event) => {
    event.preventDefault();

    const nome = document.querySelector('#name').value;
    const email = document.querySelector('#email').value;
    const senha = document.querySelector('#password').value;

    const modoCadastro = login.classList.contains('modo-cadastro');

    const url = modoCadastro
        ? 'http://localhost:8080/auth/registro'
        : 'http://localhost:8080/auth/login';

    const body = modoCadastro
        ? { nome, email, senha }
        : { email, senha };

    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(body)
        });

        if (!response.ok) {
            throw new Error('Credenciais inválidas ou email já cadastrado');
        }

        if (modoCadastro) {
            // alert('Cadastro realizado! Agora faça login.');
            toggleBtn();
        } else {
            const token = await response.text();
            localStorage.setItem('token', token);
            // alert('Login realizado com sucesso!');
            mostrarSlider();
        }

    } catch (erro) {
        alert(erro.message);
    }
});

function mostrarSlider() {
    document.querySelector('.login').style.display = 'none';
    document.querySelector('header').style.display = '';
    document.querySelector('main.container').style.display = '';
    carregarFavoritos();
}

function mostrarLogin() {
    document.querySelector('.login').style.display = 'flex';
    document.querySelector('header').style.display = 'none';
    document.querySelector('main.container').style.display = 'none';
}

if (localStorage.getItem('token')) {
    mostrarSlider();
} else {
    mostrarLogin();
}

const logoutBtn = document.querySelector('.logout');

logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('token');
    mostrarLogin();
});

let favoritosIds = new Set();

function atualizarTodosFavoritos() {
    document.querySelectorAll('[data-id]').forEach(item => {
        const id = Number(item.dataset.id);
        const ativo = favoritosIds.has(id);
        const btn = item.querySelector('.favorite, .thumb-favorite');
        if (!btn) return;

        btn.classList.toggle('ativo', ativo);
        btn.querySelector('.heart-icon').textContent = ativo ? '♥' : '♡';
    });
}

async function alternarFavorito(id) {
    const token = localStorage.getItem('token');
    const jaFavoritado = favoritosIds.has(id);

    const response = await fetch(`http://localhost:8080/favorito/${id}`, {
        method: jaFavoritado ? 'DELETE' : 'POST',
        headers: { 'Authorization': `Bearer ${token}` }
    });

    if (response.ok) {
        jaFavoritado ? favoritosIds.delete(id) : favoritosIds.add(id);
        atualizarTodosFavoritos();
    }
}

document.querySelectorAll('.favorite, .thumb-favorite').forEach(btn => {
    btn.addEventListener('click', (event) => {
        event.stopPropagation();
        const id = Number(btn.closest('[data-id]').dataset.id);
        alternarFavorito(id);
    });
});

async function carregarFavoritos() {
    const token = localStorage.getItem('token');
    if (!token) return;

    const response = await fetch('http://localhost:8080/favorito', {
        headers: { 'Authorization': `Bearer ${token}` }
    });
    const favoritos = await response.json();
    favoritosIds = new Set(favoritos.map(d => d.id));
    atualizarTodosFavoritos();
}