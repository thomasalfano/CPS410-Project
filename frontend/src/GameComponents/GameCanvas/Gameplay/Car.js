import carImage from "../Gameplay/car.png";

class Car {
  constructor(ctx, x, y) {
    this.ctx = ctx;
    this.x = x;
    this.y = y;
    this.speed = 0.1;
    this.image = new Image(200, 200);
    this.image.src = carImage;
  }

  setSpeed(speed) {
    this.speed = speed;
  }

  update() {
    this.x += this.speed;

    // for now have the car move to the end of the screen then stop
    if (this.x > this.ctx.canvas.width - 200) {
      this.speed = 0;
    }
  }

  draw() {
    this.ctx.drawImage(this.image, this.x, this.y, 200, 200);
  }
}

export default Car;
