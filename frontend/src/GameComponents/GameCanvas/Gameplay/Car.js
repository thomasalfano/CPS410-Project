import carImage from "../Gameplay/car.png";

class Car {
  constructor(ctx, x, y) {
    this.ctx = ctx;
    this.x = x;
    this.y = y;
    this.speed = 0.0;
    this.image = new Image(250, 150);
    this.currentStoppingPoint = 0;
    this.image.src = carImage;
  }

  setSpeed(speed) {
    this.speed = speed;
  }

  handleCorrect(questionIndex) {
    this.speed = 1;

    this.currentStoppingPoint =
      questionIndex * (this.ctx.canvas.width / 5) + 50;
  }

  update() {
    this.x += this.speed;

    // for now have the car move to the end of the screen then stop
    if (this.x > this.currentStoppingPoint) {
      this.speed = 0;
    }
  }

  draw() {
    this.ctx.drawImage(this.image, this.x, this.y, 250, 150);
  }
}

export default Car;
