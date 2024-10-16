import React from 'react';
import './App.css'; // Importing the CSS file

function App() {
  return (
    <div className="App">
      <div className="title">
        <h6>Mind Racers</h6>
      </div>
      <div className="signup-form">
        <form id="form" onSubmit={handleSubmit}>
          <label htmlFor="email">Email</label>
          <input type="email" id="email" required />

          <label htmlFor="email2">Confirm Email</label>
          <input type="email" id="email2" required />

          <label htmlFor="username">Username</label>
          <input type="text" id="username" required />

          <label htmlFor="password">Password</label>
          <input type="password" id="password" required />

          <label htmlFor="password2">Re-Enter Password</label>
          <input type="password" id="password2" required />

          <button className="submit-button" type="submit">Submit</button>
        </form>
      </div>
    </div>
  );
}

const handleSubmit = async (event) => {
  event.preventDefault();

  const form = document.getElementById('form');

  const email = document.getElementById('email').value;
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  const urlData = new URLSearchParams();

  urlData.append('email', email);
  urlData.append('username', username);
  urlData.append('password', password);

  const response = await fetch('http://localhost:8080/auth/signup', {
    	method: 'POST',
	headers: {
		'Content-Type': 'application/x-www-form-urlencoded'
	},
	body: urlData
  });

  if (response.ok) {

    const jsonResponse = await response.json();

    const p = document.createElement("p");
    form.appendChild(p);
    
    if (jsonResponse.success === 0) {
      p.innerHTML = "User already registered with this email.";
    } else if (jsonResponse.success ===1) {
      p.innerHTML = "Registration Successfull";
    }

  } else {
    
    throw new Error('POST request failed');
    
    }

};

export default App;
