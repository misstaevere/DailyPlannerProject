'use strict;'
// const output = document.getElementById('output');
const URL = '/user';

// const params = new URLSearchParams(window.location.search);
// console.log('id: ', params.get(id));

// CREATE
(function () {
    // document.getElementById('createTask').addEventListener('click', function () { // prints a list of items as they are typed in
    //     const newDiv = document.createElement('div');
    //     document.getElementById('output').appendChild(newDiv);
    // newDiv.innerText = document.getElementById('task_time').value + ' ' + document.getElementById('task_name').value + ' ' + document.getElementById('task_location').value;

    document.getElementById('myForm').addEventListener('submit', function (event) {
        event.preventDefault(); // prevents refresh/redirect when the createTask object runs
        const data = {}; // building a data object

        data.username = this.username.value;
        data.password = this.password.value;

        axios.post(URL + '/create', data)
            .then(res => console.log(res))
            .catch(err => console.log(err));
    });

    // DELETE
//     document.getElementById('delete_button').addEventListener('click', function () {
//         axios.delete(URL + '/delete' + document.getElementById('delete_input').value)
//             .then(res => console.log(res))
//             .catch(err => console.log(err));
//     });
})();

// XMLHttpRequest
// function getData() {
//     request.open('GET', 'http://localhost:8082/task/getAll');

//     request.onload = function () {
//         console.log(this);
//         document.getElementById('output').innerText = this.responseText;
//     }

//     request.send();
// }

// FETCH
// function getData() {
//     fetch('http://localhost:8082/task/getAll')
//     .then(res => res.json()) // always need tis line to convert to JSON format
//     .then(json => output.innerText = JSON.stringify(json, undefined, 2))
//     .catch(err => console.log(err));
// }

// AXIOS
// function getData() {
//     axios.get(URL + '/read')
//         .then(res => output.innerText = JSON.stringify(res.data, undefined, 2))
//         .catch(err => console.log(err));
// }

// function makeElement(eleType, text, appendTo, className) {
//     const element = document.createElement(eleType);
//     taskDate.innerText = text;
//     element.className = className;
//     appendTo.appendChild(element);
//     return element;
// }

// function getDataElements() {
//     axios.get(URL + '/read')
//         .then(res => {
//             bigOutput.innerText = '';
//             res.data.forEach(task => { // res.data is the response object (array)
//                 const taskDiv = makeElement('div', '', bigOutput, 'card w-50 m-2');
//                 taskDiv.taskId = 'task' + i; // Opens a new window and adds the id as a query parameter
//                 taskDiv.addEventListener('click', function () {
//                     window.location = './details.html?id=' + task.taskId;
//                 });
//                 makeElement('h2', `${task.taskDate}`, taskDiv, 'card-body');
//                 makeElement('p', `${task.taskTime}`, taskDiv, 'card-body');
//                 makeElement('p', `${task.taskName}`, taskDiv, 'card-title');
//                 makeElement('p', `${task.taskLocation}`, taskDiv, 'card-text');
//             })
//         })
//         .catch(err => console.log(err));
// }