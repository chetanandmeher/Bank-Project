// Sample data for the table
 var citiesData = [
     { id: '1', state: 'Chhattisgarh', district: 'Raipur', city: 'Naya Raipur' },
     { id: '2', state: 'Odisha', district: 'Bhubaneswar', city: 'Cuttack' },
     { id: '3', state: 'Maharashtra', district: 'Mumbai', city: 'Pune' },
     { id: '4', state: 'Madhya Pradesh', district: 'Bhopal', city: 'Indore' }
 ];


// Function to dynamically generate table row
function generateTableRows() {
    var tableBody = document.getElementById('districtTableBody');
    tableBody.innerHTML = ''; // Clear any existing rows

    districtsData.forEach(function(district) {
        var row = document.createElement('tr');

        // Create table data for state name
        var stateCell = document.createElement('td');
        stateCell.id = district.stateId;
        stateCell.innerText = district.state;
        row.appendChild(stateCell);

        // Create table data for district name
        var districtCell = document.createElement('td');
        districtCell.id = district.districtId;
        districtCell.innerText = district.district;
        row.appendChild(districtCell);

        // Create Edit button for district cell
        var actionCell = document.createElement('td');
        actionCell.id = district.districtId + '-action';
        actionCell.style.textAlign = 'right'; // Align to the right
        var districtEditButton = document.createElement('button');
        districtEditButton.className = 'btn btn-primary edit-btn';
        districtEditButton.innerText = 'Edit';
        districtEditButton.onclick = function() {
            editRow(district.districtId);
        };
        actionCell.appendChild(districtEditButton);
        row.appendChild(actionCell);

        tableBody.appendChild(row);
    });
}

// Function to edit row for district cell
function editRow(districtId) {
    var districtCell = document.getElementById(districtId);
    var currentDistrict = districtCell.innerText;

    // Replace the text with input field
    var inputField = document.createElement('input');
    inputField.type = 'text';
    inputField.className = 'form-control';
    inputField.value = currentDistrict;

    // Replace cell content with input field
    districtCell.innerHTML = '';
    districtCell.appendChild(inputField);

    // Replace Edit button with save button
    var actionCell = document.getElementById(districtId + '-action');
    actionCell.innerHTML = '';
    // create a save button
    var saveButton = document.createElement('button');
    saveButton.className = 'btn btn-success save-btn';
    saveButton.innerText = 'Save';
    saveButton.onclick = function() {
        saveRow(districtId);
    };
    actionCell.appendChild(saveButton); // push the save button to action cell
}

// Function to save row
function saveRow(districtId) {
    var inputField = document.getElementById(districtId).querySelector('input');
    var newValue = inputField.value;

    // Update the district in the data array
    var district = districtsData.find(d => d.districtId === districtId);
    district.name = newValue;

    // Replace input field with updated text
    var districtCell = document.getElementById(districtId);
    districtCell.innerHTML = newValue;

    // Replace Save Button with Edit button
    var actionCell = document.getElementById(districtId + '-action');
    actionCell.innerHTML = '';
    var editButton = document.createElement('button');
    editButton.className = 'btn btn-primary edit-btn';
    editButton.innerText = 'Edit';
    editButton.onclick = function() {
        editRow(districtId);
    };
    actionCell.appendChild(editButton);
}

// Call the function to generate initial table rows
generateTableRows();

