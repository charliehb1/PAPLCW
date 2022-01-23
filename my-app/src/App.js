import './App.css';

function App() {
  //document.getElementById("questionPlaceHolder").innerHTML = response.json();
  var res = getQuestion().then(response => {
    document.getElementById("questionPlaceHolder").innerHTML = response.question;
  });
  //
  return (
    <div className="App">
      <header className="App-header">
        <p id="questionPlaceHolder">
          This is question place holder
        </p>
        <button onClick={() => sendResponse('Yes')}>Yes</button>
        <button onClick={() => sendResponse('No')}>No</button>
      </header>
    </div>
  );
}
function sendResponse(choice) {
  console.log("User chose " + choice);
  if(choice == "Yes") {
    var address = "http://localhost:8000/nextNode?choice=Yes";
  }
  if(choice == "No") {
    var address = "http://localhost:8000/nextNode?choice=No";
  }
  var temp = fetch(address).then(response => response.json()).then(response => {
    document.getElementById("questionPlaceHolder").innerHTML = response.question;
  });

}
async function getQuestion() {
  var temp = await fetch("http://localhost:8000").then(response => response.json());
  return temp;
}

export default App;
