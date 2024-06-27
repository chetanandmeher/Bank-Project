
// Sample data for states in JSON format
var statesData = JSON.parse(`[
    { "id": "state1", "name": "Chhattisgarh" },
    { "id": "state2", "name": "Odisha" },
    { "id": "state3", "name": "Maharashtra" },
    { "id": "state4", "name": "Madhya Pradesh" }
]`);

// Function to dynamically generate table rows
function generateTableRows() {
    var tbody = document.getElementById('stateTableBody');
    tbody.innerHTML = ''; // Clear existing rows

    statesData.forEach(function(state) {
        var row = document.createElement('tr');

        // Create table data for state name
        var stateCell = document.createElement('td');
        stateCell.id = state.id;
        stateCell.innerText = state.name;
        row.appendChild(stateCell);

        // Create table data for Edit button
        var actionCell = document.createElement('td');
        actionCell.id = state.id + '-action';
        actionCell.style.textAlign = 'right'; // Align to the right
        var editButton = document.createElement('button');
        editButton.className = 'btn btn-primary edit-btn';
        editButton.innerText = 'Edit';
        editButton.onclick = function() {
            editRow(state.id);
        };
        actionCell.appendChild(editButton);
        row.appendChild(actionCell);

        tbody.appendChild(row);
    });
}

// Function to edit row
function editRow(stateId) {
    var stateCell = document.getElementById(stateId);
    var currentState = stateCell.innerText;

    // Replace text with input field
    var inputField = document.createElement('input');
    inputField.type = 'text';
    inputField.className = 'form-control';
    inputField.value = currentState;

    // Replace cell content with input field
    stateCell.innerHTML = '';
    stateCell.appendChild(inputField);

    // Replace Edit button with Save button
    var actionCell = document.getElementById(stateId + '-action');
    actionCell.innerHTML = '';
    var saveButton = document.createElement('button');
    saveButton.className = 'btn btn-success save-btn';
    saveButton.innerText = 'Save';
    saveButton.onclick = function() {
        saveRow(stateId);
    };
    actionCell.appendChild(saveButton);
}

// Function to save row
function saveRow(stateId) {
    var inputField = document.getElementById(stateId).querySelector('input');
    var newValue = inputField.value;

    // Update the state in the data array
    var state = statesData.find(s => s.id === stateId);
    state.name = newValue;

    // Replace input field with updated text
    var stateCell = document.getElementById(stateId);
    stateCell.innerHTML = newValue;

    // Replace Save button with Edit button
    var actionCell = document.getElementById(stateId + '-action');
    actionCell.innerHTML = '';
    var editButton = document.createElement('button');
    editButton.className = 'btn btn-primary edit-btn';
    editButton.innerText = 'Edit';
    editButton.onclick = function() {
        editRow(stateId);
    };
    actionCell.appendChild(editButton);
}

// Call the function to generate initial table rows
generateTableRows();