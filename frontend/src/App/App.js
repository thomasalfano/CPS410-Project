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

const handleSubmit = (event) => {
  event.preventDefault();
  const email = document.getElementById('email').value;
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  console.log(email);
  console.log(username);
  console.log(password);
};

export default App;
