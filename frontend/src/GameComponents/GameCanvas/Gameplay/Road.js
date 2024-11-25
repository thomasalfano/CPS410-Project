import roadImage from "../Gameplay/road.png";

class Road {
  constructor(ctx) {
    this.ctx = ctx;
    this.roads = [];

    // Load the road image
    this.image = new Image();
    this.image.src = roadImage;

    // Create three road segments positioned next to each other
    for (let i = 0; i < 4; i++) {
      this.roads.push({
        x: i * ctx.canvas.width, // Position them side by side
        y: ctx.canvas.height / 3, // Assuming the road starts at the top of the canvas
      });
    }

    this.speed = 2; // Speed of the scrolling background
  }

  update() {
    // Move each road segment to the left
    this.roads.forEach((road) => {
      road.x -= this.speed;

      // Reset the road's position when it moves off-screen
      if (road.x + this.ctx.canvas.width <= 0) {
        road.x = this.ctx.canvas.width * (this.roads.length - 1);
      }
    });
  }

  draw() {
    // Draw each road segment
    this.roads.forEach((road) => {
      this.ctx.drawImage(
        this.image,
        road.x,
        road.y,
        this.ctx.canvas.width,
        this.ctx.canvas.height
      );
    });
  }
}

export default Road;
