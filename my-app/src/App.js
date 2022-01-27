import './App.css';

function App() {
  getQuestion().then(response => {
    document.getElementById("questionPlaceHolder").innerHTML = response.question;
  });

  return (
    <div className="App">
      <header className="App-header">
      <p id="title">Emergency <br></br>First Aid <br></br>Pocket Guide</p>
        <div id="questionContainer">
          <p id="questionPlaceHolder">This is question place holder</p>
          <div id="buttonContainer">
            <button id="yButton" class="YNButtons" onClick={() => sendResponse('Yes')}>Yes</button>
            <button id="nButton" class="YNButtons" onClick={() => sendResponse('No')}>No</button>
          </div>
        </div>
        <div id="infoContainer">
          <button class="commonHelpInfo">CPR</button>
          <button class="commonHelpInfo">Recovery Position</button>
          <button class="commonHelpInfo">Emergency Services</button>
          <button class="commonHelpInfo">Accessing a casulty</button>
          <button class="commonHelpInfo">DRABCD</button>
        </div>
      </header>
    </div>
  );
}

function sendResponse(choice) {
  var address=null;
  if(choice === "Yes") {
    address = "http://localhost:8000/nodeResult?choice=Yes";
  }
  if(choice === "No") {
    address = "http://localhost:8000/nodeResult?choice=No";
  }
  fetch(address, { method: "POST" }).then(response => response.json()).then(response => {
    document.getElementById("questionPlaceHolder").innerHTML = response.question;
  });
}

async function getQuestion() {
  return await fetch("http://localhost:8000").then(response => response.json());
}

export default App;
