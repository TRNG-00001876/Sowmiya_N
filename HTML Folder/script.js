// Sample product data (replace this with your actual product data)
const products = [
    {
        name: "Elegant Dress",
        price: "$49.99",
        image: "eg1.jpeg"
    },
    {
        name: "Casual Dress",
        price: "$29.99",
        image: "eg2.jpeg"
    },
    {
        name: "Formal Dress",
        price: "$69.99",
        image: "eg3.jpeg"
    }
];

// Function to create product cards and append them to the products section
function renderProducts() {
    const productsSection = document.querySelector('.products');
    products.forEach(product => {
        const card = document.createElement('div');
        card.classList.add('product-card');

        const image = document.createElement('img');
        image.src = `images/${product.image}`;
        card.appendChild(image);

        const name = document.createElement('h3');
        name.textContent = product.name;
        card.appendChild(name);

        const price = document.createElement('p');
        price.textContent = product.price;
        card.appendChild(price);

        productsSection.appendChild(card);
    });
}

// Render the products when the page loads
window.onload = renderProducts;
