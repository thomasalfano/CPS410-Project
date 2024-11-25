import "./Option.css";

function Option({ text, onClick }) {
  return (
    <button onClick={onClick} className="option">
      {text}
    </button>
  );
}

export default Option;
