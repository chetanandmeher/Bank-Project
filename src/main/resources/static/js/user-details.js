function addAccount() {
    const accountContainer = document.getElementById('account-container');
    const newAccount = document.createElement('div');
    newAccount.classList.add('row', 'account-row');
    newAccount.innerHTML = `
        <div class="col-12 d-flex justify-content-between align-items-center">
            <div class="form-section-title flex-grow-1">Account</div>
            <button type="submit" class="btn btn-primary btn-lg">Save</button>
        </div>
        <div class="col-xxl-3 col-xl-3 col-lg-4 col-md-4 col-sm-4 col-12">
            <div class="mb-3">
                <label for="inputAccountNumber" class="form-label">Account Number</label>
                <input type="number" class="form-control" placeholder="Enter Account No.">
            </div>
        </div>
        <div class="col-xxl-3 col-xl-3 col-lg-4 col-md-4 col-sm-4 col-12">
            <div class="mb-3">
                <label for="inputAccountType" class="form-label">Account Type</label>
                <select class="form-select account-type-select">
                    <option selected disabled>Choose...</option>
                    ${getAccountTypeOptions()}
                </select>
            </div>
        </div>
        <div class="col-xxl-3 col-xl-3 col-lg-4 col-md-4 col-sm-4 col-12">
            <div class="mb-3">
                <label for="inputBranch" class="form-label">Branch</label>
                <select class="form-select branch-select">
                    <option selected disabled>Choose...</option>
                    ${getBranchOptions()}
                </select>
            </div>
        </div>
        <div class="col-xxl-3 col-xl-3 col-lg-4 col-md-4 col-sm-4 col-12">
            <div class="mb-3">
                <label for="inputROI" class="form-label">ROI</label>
                <input type="number" class="form-control" placeholder="Enter ROI" step="0.01">
            </div>
        </div>
        <div class="col-xxl-3 col-xl-3 col-lg-4 col-md-4 col-sm-4 col-12">
            <div class="mb-3">
                <label for="inputBalance" class="form-label">Balance</label>
                <input type="number" class="form-control" placeholder="Enter Balance" step="0.01" max="9999999999999.99">
            </div>
        </div>
    `;
    accountContainer.appendChild(newAccount);
}

function getAccountTypeOptions() {
    const accountTypes = ['Savings', 'Checking', 'Credit']; // Example types
    return accountTypes.map(type => `<option value="${type}">${type}</option>`).join('');
}

function getBranchOptions() {
    const branches = ['New York', 'Los Angeles', 'Chicago']; // Example branches
    return branches.map(branch => `<option value="${branch}">${branch}</option>`).join('');
}
