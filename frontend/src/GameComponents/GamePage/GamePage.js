import GameCanvas from "../GameCanvas/GameCanvas";
import Option from "../Option/Option";

function GamePage({ options }) {
  return (
    <>
      <div>
        <GameCanvas />
      </div>

      <div>
        {options.map((option) => (
          <Option key={option} text={option} />
        ))}
      </div>
    </>
  );
}

export default GamePage;
