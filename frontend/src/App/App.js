import React, { useState } from 'react';
import './App.css';
import './Form.css';


function Form() {

    const handleSubmit = async (event) => {

        event.preventDefault();
      
            // Simple validation
            if (email !== confirmEmail) {
              alert("Emails do not match!");
              event.preventDefault();
              return;
            }
            if (password !== password2) {
              alert("Passwords do not match!");
              event.preventDefault();
              return;
            }
            
      
        const form = document.getElementById('form');
        const email = document.getElementById('email').value;
        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;
        const confirmEmail = document.getElementById('confirmEmail').value;
        const password2 = document.getElementById('password2').value;

      
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
  
    <form id="form" onSubmit={handleSubmit}></form>

    const [activeTab, setActiveTab] = useState('signup');
    const[showPassword, setShowPassword] = useState(false);
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);
    const [inputValues, setInputValues] = useState({
      userName: '',
      email: '',
      confirmEmail: '',
      password: '',
      password2: '',
    });
  
    const handleInputChange = (e) => {
      const { name, value } = e.target;
      setInputValues({ ...inputValues, [name]: value });
    };
  
    const handleTabClick = (tab) => {
      setActiveTab(tab);
    };

    const togglePasswordVisibility = () => {
      setShowPassword(!showPassword);
  };
  
    return (
      <div className="form">
        <ul className="tab-group">
          <li className={`tab ${activeTab === 'signup' ? 'active' : ''}`}>
            <a href="#signup" onClick={() => handleTabClick('signup')}>Sign Up</a>
          </li>
          <li className={`tab ${activeTab === 'login' ? 'active' : ''}`}>
            <a href="#login" onClick={() => handleTabClick('login')}>Log In</a>
          </li>
        </ul>
  
  
        <div className="tab-content">
  <div id="signup" style={{ display: activeTab === 'signup' ? 'block' : 'none' }}>
    <h1>Sign Up for Free.</h1>
    <form action="/" method="post">
      <div className="field-wrap">
        <label className={inputValues.userName ? 'active highlight' : ''}>
          Username<span className="req">*</span>
        </label>
        <input
          type="text"
          name="userName"
          value={inputValues.userName}
          required
          autoComplete="off"
          onChange={handleInputChange}
        />
      </div>

      <div className="field-wrap">
        <label className={inputValues.email ? 'active highlight' : ''}>
          Email Address<span className="req">*</span>
        </label>
        <input
          type="email"
          name="email"
          value={inputValues.email}
          required
          autoComplete="off"
          onChange={handleInputChange}
        />
      </div>

      <div className="field-wrap">
        <label className={inputValues.confirmEmail ? 'active highlight' : ''}>
          Confirm Email Address<span className="req">*</span>
        </label>
        <input
          type="email"
          name="confirmEmail"
          value={inputValues.confirmEmail}
          required
          autoComplete="off"
          onChange={handleInputChange}
        />
      </div>

      <div className="field-wrap">
        <div className="eye-container">
          <button type="button" className = "eye" onClick={togglePasswordVisibility}>
            <i className={showPassword ? "fa fa-eye-slash" : "fa fa-eye"}></i>
          </button>
        </div>

        <label className={inputValues.password ? 'active highlight' : ''}>
          Password<span className="req">*</span>
        </label>

        <input
          type={showPassword ? 'text' : 'password'}
          name="password"
          value={inputValues.password}
          required
          autoComplete="off"
          onChange={handleInputChange}
        />

      </div>

      <div className="field-wrap">
        <label className={inputValues.password2 ? 'active highlight' : ''}>
          Confirm Password<span className="req">*</span>
        </label>
        <input
         type={showPassword ? 'text' : 'password'}
          name="password2"
          value={inputValues.password2}
          required
          autoComplete="off"
          onChange={handleInputChange}
        />
      </div>

      <button type="submit" className="button button-block">
        Get Started
      </button>
    </form>
  </div>
  
          <div id="login" style={{ display: activeTab === 'login' ? 'block' : 'none' }}>
            <h1>Welcome Back!</h1>
            <form action="/" method="post">
              <div className="field-wrap">
                <label className={inputValues.email ? 'active highlight' : ''}>
                  Email Address<span className="req">*</span>
                </label>
                <input
                  type="email"
                  name="email"
                  value={inputValues.email}
                  required
                  autoComplete="off"
                  onChange={handleInputChange}
                />
              </div>
  
              <div className="field-wrap">
                <label className={inputValues.password ? 'active highlight' : ''}>
                  Password<span className="req">*</span>
                </label>
                <input
                  type="password"
                  name="password"
                  value={inputValues.password}
                  required
                  autoComplete="off"
                  onChange={handleInputChange}
                />
              </div>
  
              <p className="forgot">
                <a href="#">Forgot Password?</a>
              </p>
  
              <button className="button button-block">Log In</button>
            </form>
          </div>
        </div>
      </div>
    );
  }

function Title() {
    return (
        <div className="title">
        <h6>Mind Racers</h6>
        </div>
    )
}  

function App() {

  return (
    <div className="App">
        <div className="title">
        <Title />
        </div>
        <div className="form-container">
            <Form />
        </div>
    </div>
  );
}

export default App;