//Ensure the script run after the DOM is fully loaded
document.addEventListener("DOMContentLoaded", function() {

    // Select all the element  with class "edit-btn" and attach an event listener to each
    document.querySelectorAll('.edit-btn').forEach(function(button) {
        // When an edit-btn is clicked, call the editRow function with the data-id of the clicked row
        button.addEventListener('click',function() {
            editRow(this.getAttribute('data-id'));  // Get the data-id of the clicked row and pass it to the editRow function
        });

    });



    // Function to edit row
    function editRow(dataId) {
        var row = document.querySelector(`[data-id="${dataId}"]`);
        var cells = row.querySelectorAll("td");
        // Replace the cells with input cells
        cells.forEach(function(cell) {
            // skip the button
            if(!cell.querySelector("#editButton")) {
                var input = document.createElement('input');
                input.type = 'text';
                input.value = cell.innerText;
                input.style.width = '100%'; // Set width to 100% of the cell
                input.style.height = '30px'; // Set height (adjust as needed)
                input.style.boxSizing = 'border-box';
                cell.innerHTML = '';
                cell.appendChild(input);
            // change the edit button to save button
            var actionCell = row.children[row.cells.length - 1];
            actionCell.innerHTML ="";
            var saveButton = document.createElement('button');
            saveButton.className = 'btn btn-success btn-sm save-btn';
            saveButton.innerText = 'Save';
            saveButton.style.marginRight = "10px";
            saveButton.style.width = "75px";
            saveButton.style.height = "35px";
            saveButton.onclick = function() {
                saveRow(dataId);
            };
            actionCell.appendChild(saveButton);
            }
        });
    }
    // Function to save row
    function saveRow(dataId) {
        var row = document.querySelector(`[data-id="${dataId}"]`);
        var cells = row.querySelectorAll("td");
        cells.forEach(function(cell) {
            // skip the button
            if(!cell.querySelector("#editButton")) {
                var value = cell.querySelector('input').value;
                cell.innerText = value;
                // change the save button to edit button
                var actionCell = row.children[row.cells.length - 1];
                actionCell.innerHTML ="";
                var editButton = document.createElement('button');
                editButton.className = 'btn btn-primary edit-btn';
                editButton.innerText = 'Edit';
                editButton.setAttribute('data-id', dataId);
                editButton.style.marginRight = "10px";
                editButton.style.width = "75px";
                editButton.style.height = "35px";
                editButton.addEventListener('click', function() {
                    editRow(this.getAttribute('data-id'));
                });
                actionCell.appendChild(editButton);
            }
        });
    }




});
