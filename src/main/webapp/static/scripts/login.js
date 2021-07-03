let userType = localStorage.getItem('userType');

let userTypeButtons = document.querySelectorAll('.user-type-button');
userTypeButtons.forEach((button) => {
  button.addEventListener('click', (e) => {
    userType = e.target.dataset.userType;
    localStorage.setItem('userType', userType);
    location.reload();
  });
});

let formTitle = document.querySelector('.form-title > h1');
let userIdTitle = document.querySelector('label[for="userId"]');

// Voter Login
if (!userType || userType === 'voter') {
  localStorage.setItem('userType', 'voter');
  let userTypeButton = document.querySelector('#voter-button');

  formTitle.textContent = 'Voter Login';
  userIdTitle.textContent = 'Student Id';
  userTypeButton.style.backgroundColor = '#99d98c';
  userTypeButton.style.border = 'solid black 1px';
}

// Candidate Login
else if (userType === 'candidate') {
  let userTypeButton = document.querySelector('#candidate-button');

  formTitle.textContent = 'Candidate Login';
  userIdTitle.textContent = 'Student Id';
  userTypeButton.style.backgroundColor = '#99d98c';
  userTypeButton.style.border = 'solid black 1px';
}

// Admin Login
else if (userType === 'admin') {
  localStorage.setItem('userType', 'admin');
  let userTypeButton = document.querySelector('#admin-button');

  formTitle.textContent = 'Admin Login';
  userIdTitle.textContent = 'Staff Id';
  userTypeButton.style.backgroundColor = '#99d98c';
  userTypeButton.style.border = 'solid black 1px';
}

const loginButton = document.querySelector('#login-button');
loginButton.onclick = (e) => {
  const form = document.querySelector('form');
  const input = document.createElement('input');
  input.type = 'hidden';
  input.name = 'userType';
  input.value = userType;

  form.appendChild(input);
};
