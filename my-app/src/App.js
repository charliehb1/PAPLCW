import React from 'react';
import './App.css';

// This is the main part which displayed on the web page and the main function of the app, which is injected into the root element.
function App() {
  initApp();
  return (
    <div className="App">
      <CprModal />
      <RecoveryModal />
      <EmergencyModal />
      <CasultyModal />
      <DRABCDModal />
      <header className="App-header">
        <p id="title">Emergency <br></br>First Aid <br></br>Pocket Guide</p>
        <div id="questionContainer">
          <p id="questionPlaceHolder"></p>
          <div id="buttonContainer">
            <button id="yButton" class="Buttons" onClick={() => sendResponse('Yes')}>Yes</button>
            <button id="nButton" class="Buttons" onClick={() => sendResponse('No')}>No</button>
            <button id="cButton" class="Buttons" onClick={() => sendResponse('Yes')}>Continue</button>
            <button id="rButton" class="Buttons" onClick={() => restartMap()}>Restart</button>
          </div>
        </div>
        <div id="infoContainer">
          <button class="commonHelpInfo" onClick={() => openMenu("cprModal")}>CPR</button>
          <button class="commonHelpInfo" onClick={() => openMenu("recoveryModal")}>Recovery Position</button>
          <button class="commonHelpInfo" onClick={() => openMenu("emergencyServicesModal")}>Emergency Services</button>
          <button class="commonHelpInfo" onClick={() => openMenu("casultyModal")}>Accessing a casulty</button>
          <button class="commonHelpInfo" onClick={() => openMenu("DRABCDModal")}>DRABCD</button>
        </div>
      </header>
    </div>
    
  );
}
// The CprModal is the custom pop-up modal which shows the user cpr instructions
function CprModal() {
  return (
    <div>
      <div id="cprModal" class="modal">
        <span class="close" onClick={() => closeMenu("cprModal")}>&times;</span>
        <h1>CPR</h1>
        <p>To carry out a chest compression:</p>
        <ol>
          <li>Place the heel of your hand on the breastbone at the centre of the person's chest. Place your other hand on top of your first hand and interlock your fingers.</li>
          <li>Position yourself with your shoulders above your hands.</li>
          <li>Using your body weight (not just your arms), press straight down by 5 to 6cm (2 to 2.5 inches) on their chest.</li>
          <li>Keeping your hands on their chest, release the compression and allow the chest to return to its original position.</li>
          <li>Repeat these compressions at a rate of 100 to 120 times a minute until an ambulance arrives or you become exhausted.</li>
        </ol>
      </div>
    </div>
  );
}
// The RecoveryModal function is the custom pop-up modal which shows the user recovery position instructions
function RecoveryModal() {
  return (
    <div>
      <div id="recoveryModal" class="modal">
        <span class="close" onClick={() => closeMenu("recoveryModal")}>&times;</span>
        <h1>Recovery</h1>
        <p>Putting someone in the recovery position will keep their airway clear and open. It also ensures that any vomit or fluid won't cause them to choke.</p>
        <ol>
          <li>With the person lying on their back, kneel on the floor at their side.</li>
          <li>Extend the arm nearest you at a right angle to their body with their palm facing up.</li>
          <li>Take their other arm and fold it so the back of their hand rests on the cheek closest to you, and hold it in place.</li>
          <li>Use your free hand to bend the person's knee farthest from you to a right angle.</li>
          <li>Carefully roll the person onto their side by pulling on the bent knee.</li>
          <li>Their bent arm should be supporting the head, and their extended arm will stop you rolling them too far.</li>
          <li>Make sure their bent leg is at a right angle.</li>
          <li>Open their airway by gently tilting their head back and lifting their chin, and check that nothing is blocking their airway.</li>
          <li>Stay with the person and monitor their condition until help arrives.</li>
        </ol>
      </div>
    </div>
  );
}
// The EmergencyModal function is the custom pop-up modal which shows the user emergency service contacts
function EmergencyModal() {
  return (
    <div>
      <div id="emergencyServicesModal" class="modal">
        <span class="close" onClick={() => closeMenu("emergencyServicesModal")}>&times;</span>
        <h1>Emergency Services</h1>
        <p>Emergency numbers and useful contacts</p>
        <ul>
          <li>111 - Emergency dentist and emergencies that don't require an ambulance</li>
          <li>116 123 - Samaritans </li>
          <li>999 - Ambulance</li>
          <li>999 - Police</li>
          <li>999 - Fire</li>
          <li>101 - Non emergency police</li> 
        </ul>
      </div>
    </div>
  );
}
// The CasultyModal function is the custom pop-up modal which shows the user how to access a casulty
function CasultyModal() {
  return (
    <div>
      <div id="casultyModal" class="modal">
        <span class="close" onClick={() => closeMenu("casultyModal")}>&times;</span>
        <h1>Accessing a casulty</h1>
        <p>Key points to remember</p>
        <ul>
          <li>A - Alert, is the person fully awake? they will have their eyes open and will respond to talking.</li>
          <li>V - Voice, can the person respond back to you when you talk to them?</li>
          <li>P - Pain, does the person respond to any pain? (eyes, voice, movement)</li>
          <li>U - Unresponsive, the person doesn't give any eye voice or movement response to voice or pain.</li>  
        </ul>
      </div>
    </div>
  );
}
// The DRABCDModal function is the custom pop-up modal which shows the user key points (DRABCD)
function DRABCDModal() {
  return (
    <div>
      <div id="DRABCDModal" class="modal">
        <span class="close" onClick={() => closeMenu("DRABCDModal")}>&times;</span>
        <h1>DRABCD</h1>
        <p>Key points to remember</p>
        <ul>
          <li>D - Danger, ensure there is no dnager and it is safe.</li>
          <li>R - Response, look for response and approch them by their feet to avoid them moving their neck</li>
          <li>A - Airways, open the airway by lifting the chin up</li>
          <li>B - Breathing, look and listen for normal breathing</li>
          <li>C - Call 999 & CPR, stay with the person and get an AED if possible</li>
          <li>D - Defibrilation, switch on and follow the prompts of a AED if required in conjunction with CPR</li>   
        </ul>
      </div>
    </div>
  );
}
// Closes the modal menus when the user clicks the cross, and sets the fade-out animation, and the main areas opacity back to normal (in focus)
function closeMenu(name) {
  document.getElementById(name).style.animation = "fadeOut linear 0.5s";
  document.getElementById(name).style.opacity = 0;
  setTimeout(() => {
    document.getElementById(name).style.display = "none";
    document.getElementsByClassName("App-header")[0].style.opacity = "1";
  }, 500);
}
// Runs at the getNodes function and sets up the page.
function initApp() {
  getNodes().then(response => {
    document.getElementById("questionPlaceHolder").innerHTML = response.question;
  });
}
// This function resets the buttons, and starts the app again by generating a new user session.
function restartMap() {
  initApp();
  document.getElementById("yButton").style.display = "inline-block";
  document.getElementById("nButton").style.display = "inline-block";
  document.getElementById("rButton").style.display = "none";
}
// Sends a response to the server, either yes or no, the server responds with a question and the buttons are updated accordingly.
function sendResponse(choice) {
  var address=null;
  if(choice === "Yes") {
    address = "http://localhost:8000/nodeResult?choice=Yes";
  }
  if(choice === "No") {
    address = "http://localhost:8000/nodeResult?choice=No";
  }
  fetch(address, { method: "POST" }).then(response => response.json()).then(response => {

    document.getElementById("questionPlaceHolder").style.opacity = "0";
    setTimeout(function() {
      document.getElementById("questionPlaceHolder").innerHTML = response.question;
      document.getElementById("questionPlaceHolder").style.opacity = "1";
    }, 500);


    if(response.isQuestion == "false") {
      document.getElementById("yButton").style.display = "none";
      document.getElementById("nButton").style.display = "none";
      document.getElementById("cButton").style.display = "inline-block";
    }
    if(response.isQuestion == "true") {
      document.getElementById("yButton").style.display = "inline-block";
      document.getElementById("nButton").style.display = "inline-block";
      document.getElementById("cButton").style.display = "none";
    }
    if(response.question == "You have reached the end of the decision map.") {
      document.getElementById("yButton").style.display = "none";
      document.getElementById("nButton").style.display = "none";
      document.getElementById("cButton").style.display = "none";
      document.getElementById("rButton").style.display = "inline-block";
    }
  });
}
// Opens the modal menus with a fade-in animation and sets the main background to darken at 0.5 opacity.
function openMenu(name) {
  document.getElementById(name).style.animation = "fadeIn linear 0.5s";
  document.getElementById(name).style.opacity = 1;
  document.getElementsByClassName("App-header")[0].style.opacity = "0.5";
  document.getElementById(name).style.display = "block";
}
// Gets the inital setup from the Java backend creating the user session and returns the response (First question)
async function getNodes() {
  return await fetch("http://localhost:8000", { method: "GET" }).then(response => response.json());
}
export default App;
