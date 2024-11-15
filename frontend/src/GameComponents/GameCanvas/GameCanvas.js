import { useEffect, useRef } from "react";
import Car from "./Gameplay/Car";
import Road from "./Gameplay/Road";

function GameCanvas() {
  const canvasRef = useRef(null);
  const carRef = useRef(null);
  const roadRef = useRef(null);

  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");

    // players car
    carRef.current = new Car(ctx, 0, (3 / 4) * ctx.canvas.height - 80);
    const car = carRef.current;

    // the infinite scroll road
    roadRef.current = new Road(ctx);
    const road = roadRef.current;

    function update() {
      car.update();
      road.update();
    }

    function draw() {
      // Clear the canvas before drawing
      ctx.clearRect(0, 0, canvas.width, canvas.height);

      // Update and draw the car
      update();

      // order matters here, want to draw the road first to avoid it rendering over the car
      road.draw();
      car.draw();

      window.requestAnimationFrame(draw);
    }

    draw();
  }, []); // Added dependency array to run the effect only once

  return (
    <canvas
      ref={canvasRef}
      data-testid="GameCanvas"
      id="GameCanvas"
      width="800"
      height="600"
      style={{ backgroundColor: "black" }}
    ></canvas>
  );
}

export default GameCanvas;
