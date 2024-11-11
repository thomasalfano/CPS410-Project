// class to be used for calculating a running score while answering questions
// TODO: function to increment / decrement score
// TODO: function to start timer and end timer
class ScoreCalculator {
  constructor() {
    this.startTime;
    this.endTime;
    this.runningScore = 0;
  }

  startTimer() {
    this.startTime = Math.floor(Date.now() / 1000);
  }

  endTimer() {
    this.endTime = Math.floor(Date.now() / 1000);
  }

  getScoreChange() {
    let seconds = this.endTime - this.startTime;

    if (seconds < 5) return 10;
    else if (seconds < 10) return 7;
    else if (seconds < 15) return 5;
    else if (seconds < 20) return 3;
    else return 0;
  }

  updateScore() {
    this.runningScore += getScoreChange();
  }
}
