import React, { useState } from 'react';
import './App.css'; // Importing the CSS file

function App() {

  const [email, setEmail] = useState('');
  const [confirmEmail, setConfirmEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [password2, setPassword2] = useState('');



const handleSubmit = async (event) => {

  event.preventDefault();

      // Simple validation
      if (email !== confirmEmail) {
        alert("Emails do not match!");
        return;
      }
      if (password !== password2) {
        alert("Passwords do not match!");
        return;
      }

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

return (
  <div className="App">
    <div className="title">
      <h6>Mind Racers</h6>
    </div>
    <div className="signup-form">
      <form id="form" onSubmit={handleSubmit}>
        <label htmlFor="email">Email</label>
        <input
          type="email"
          id="email"
          required
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <label htmlFor="email2">Confirm Email</label>
        <input
          type="email"
          id="email2"
          required
          value={confirmEmail}
          onChange={(e) => setConfirmEmail(e.target.value)}
        />

        <label htmlFor="username">Username</label>
        <input
          type="text"
          id="username"
          required
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />

        <label htmlFor="password">Password</label>
        <input
          type="password"
          id="password"
          required
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />

        <label htmlFor="password2">Re-Enter Password</label>
        <input
          type="password"
          id="password2"
          required
          value={password2}
          onChange={(e) => setPassword2(e.target.value)}
        />

        <button className="submit-button" type="submit">Submit</button>
      </form>
    </div>
  </div>
);

}

export default App;
