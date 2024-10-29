import React, { useState } from "react";
import Home from "../Home/Home.js";
import GamePage from "../GameComponents/GamePage/GamePage.js";
import "./App.css";
import { Routes, Route } from "react-router-dom";

function Title() {
  return (
    <div className="title">
      <h6>Mind Racers</h6>
    </div>
  );
}

function App() {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/game" element={<GamePage options={[1, 2, 3, 4]} />} />
      </Routes>
    </div>
  );
}

export default App;
