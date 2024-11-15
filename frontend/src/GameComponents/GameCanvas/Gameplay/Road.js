class Road {
  constructor(ctx) {
    this.ctx = ctx;
    this.lines = [];

    for (let i = 0; i < 5; i++) {
      let line = new Line(
        this.ctx,
        (this.ctx.canvas.width / 5) * i,
        (3 / 4) * this.ctx.canvas.height + 50
      );
      this.lines.push(line);
    }
  }

  update() {
    this.lines.map((line) => {
      line.update();
    });
  }

  draw() {
    this.lines.map((line) => {
      line.draw();
    });
  }
}

class Line {
  constructor(ctx, x, y) {
    this.ctx = ctx;
    this.x = x;
    this.y = y;
    this.speed = 2;
  }

  update() {
    this.x -= this.speed;

    if (this.x + 50 < 0) {
      this.x = this.ctx.canvas.width;
    }
  }

  draw() {
    this.ctx.fillStyle = "white";
    this.ctx.fillRect(this.x, this.y, 50, 5);
  }
}

export default Road;
