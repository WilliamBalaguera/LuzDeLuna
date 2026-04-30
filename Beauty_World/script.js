const cart = [];
const cartCountEl = document.getElementById('cartCount');
const cartModal = document.getElementById('cartModal');
const openCartButton = document.getElementById('openCartButton');
const closeCartButton = document.getElementById('closeCartButton');
const cartItemsEl = document.getElementById('cartItems');
const toast = document.getElementById('toast');
const productGrid = document.getElementById('productGrid');
const contactForm = document.getElementById('contactForm');
const paymentForm = document.getElementById('paymentForm');

function showToast(message) {
    toast.textContent = message;
    toast.classList.add('show');
    setTimeout(() => toast.classList.remove('show'), 2500);
}

function updateCartCount() {
    cartCountEl.textContent = cart.length;
}

function renderCart() {
    cartItemsEl.innerHTML = '';

    if (cart.length === 0) {
        const empty = document.createElement('p');
        empty.className = 'empty-state';
        empty.textContent = 'Tu carrito está vacío.';
        cartItemsEl.appendChild(empty);
        return;
    }

    cart.forEach((item, index) => {
        const itemEl = document.createElement('div');
        itemEl.className = 'cart-item';
        itemEl.innerHTML = `
            <div>
                <strong>${item.name}</strong>
                <span>${item.description}</span>
            </div>
            <div>${item.price}</div>
        `;
        cartItemsEl.appendChild(itemEl);
    });
}

function handleAddToCart(productName) {
    const button = document.querySelector(`[data-name="${productName}"]`);
    const card = button.closest('.product-card');
    const name = productName;
    const description = card.querySelector('p').textContent;
    const price = card.querySelector('.price').textContent;

    cart.push({ name, description, price });
    updateCartCount();
    renderCart();
    showToast('Producto agregado al carrito');

    const formData = new FormData();
    formData.append('action', 'addToCart');
    formData.append('productName', name);

    fetch('/tienda', {
        method: 'POST',
        body: formData,
        credentials: 'same-origin'
    }).then(response => {
        if (!response.ok) {
            throw new Error('No se pudo agregar el producto.');
        }
    }).catch(() => {
        showToast('Error al conectar con el servidor.');
    });
}

productGrid.addEventListener('click', (event) => {
    const button = event.target.closest('.buy-button');
    if (!button) return;
    const productName = button.dataset.name;
    handleAddToCart(productName);
});

openCartButton.addEventListener('click', () => {
    cartModal.classList.add('open');
    cartModal.setAttribute('aria-hidden', 'false');
    renderCart();
});

closeCartButton.addEventListener('click', () => {
    cartModal.classList.remove('open');
    cartModal.setAttribute('aria-hidden', 'true');
});

window.addEventListener('click', (event) => {
    if (event.target === cartModal) {
        closeCartButton.click();
    }
});

contactForm.addEventListener('submit', (event) => {
    event.preventDefault();
    const { name, email } = event.target.elements;
    showToast(`Gracias, ${name.value}. Pronto te contactaremos a ${email.value}.`);
    contactForm.reset();
});

paymentForm.addEventListener('submit', (event) => {
    event.preventDefault();

    if (cart.length === 0) {
        showToast('Agrega al menos un producto antes de pagar.');
        return;
    }

    const formData = new FormData(paymentForm);
    formData.append('action', 'processPayment');

    fetch('/tienda', {
        method: 'POST',
        body: formData,
        credentials: 'same-origin'
    }).then(response => {
        if (response.ok) {
            showToast('Pago procesado correctamente. ¡Gracias por tu compra!');
            cart.length = 0;
            updateCartCount();
            renderCart();
            paymentForm.reset();
        } else {
            throw new Error('Error en el pago');
        }
    }).catch(() => {
        showToast('No se pudo procesar el pago. Intenta de nuevo.');
    });
});

updateCartCount();
