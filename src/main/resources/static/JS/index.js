'use strict;'
const URL = '/user';

// CREATE
(function () {
    
    document.getElementById('myForm').addEventListener('submit', function (event) {
        event.preventDefault(); // prevents refresh/redirect when the createTask object runs
        const data = {}; // building a data object

        data.username = this.username.value;
        data.password = this.password.value;

        axios.post(URL + '/create', data)
            .then(res => console.log(res))
            .catch(err => console.log(err));
    });
})();
