// Ensure the script runs after the DOM is fully loaded
window.addEventListener('DOMContentLoaded', function() {
    // Select all elements with the class 'edit-button' and attach a click event listener to each
    document.querySelectorAll('.edit-button').forEach(function(button) {
        button.addEventListener('click', function() {
            // When an edit button is clicked, call the toggleEditMode function
            toggleEditMode(this);
        });
    });

    // Function to toggle between 'Edit' and 'Save' mode
    function toggleEditMode(btn) {
        // Get the closest table row to the clicked button
        var row = btn.closest('tr');

        // Check if the button's text is 'Edit'
        if (btn.textContent === 'Edit') {
            // Loop through each table cell in the row
            row.querySelectorAll('td').forEach(function(cell) {
                // Skip cells that contain the button
                if (cell.querySelector('button') === null) {
                    // Get the current text value of the cell
                    var value = cell.textContent;
                    // Replace the cell's content with an input field containing the current text value
                    cell.innerHTML = '<input type="text" class="form-control" value="' + value + '">';
                }
            });
            // Change the button's text to 'Save'
            btn.textContent = 'Save';
        } else {
            // If the button's text is 'Save'
            // Loop through each table cell in the row
            row.querySelectorAll('td').forEach(function(cell) {
                // Skip cells that contain the button
                if (cell.querySelector('button') === null) {
                    // Get the value of the input field in the cell
                    var value = cell.querySelector('input').value;
                    // Replace the input field with the input value as text
                    cell.textContent = value;
                }
            });
            // Change the button's text to 'Edit'
            btn.textContent = 'Edit';

            // Prepare the updated data to be sent to the server
            var updatedData = {
                accountNumber: row.cells[0].textContent,
                customerUsername: row.cells[1].textContent,
                type: row.cells[2].textContent,
                branchName: row.cells[3].textContent,
                rateOfInterest: row.cells[4].textContent,
                balance: row.cells[5].textContent
            };

            // Log the updated data to the console (for debugging purposes)
            console.log(updatedData);

            // Uncomment and modify the AJAX call to save the updated data to the server
            // fetch('your-server-endpoint', {
            //     method: 'POST',
            //     headers: {
            //         'Content-Type': 'application/json'
            //     },
            //     body: JSON.stringify(updatedData)
            // })
            // .then(response => response.json())
            // .then(data => console.log('Data saved successfully:', data))
            // .catch(error => console.error('Error saving data:', error));
        }
    }
});
