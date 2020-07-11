'use strict;'
// const request = new XMLHttpRequest();
const output = document.getElementById('output');
const bigOutput = document.getElementById('bigOutput');
const URL = '/task';

const params = new URLSearchParams(window.location.search);
console.log('id: ', params.get('taskId'));

(function () {
    // CREATE
    document.getElementById('myForm').addEventListener('submit', function (event) {
        event.preventDefault(); // prevents refresh/redirect when the createTask object runs
        const data = {}; // building a data object
        data.taskDate = this.taskDate.value;
        data.taskTime = this.taskTime.value;
        data.taskName = this.taskName.value;
        data.taskLocation = this.taskLocation.value;

        axios.post(URL + '/create', data)
            .then(res => alert("You have successfully added " + res.data.taskName + " to your calendar!"))
            .catch(err => console.log(err));
    });

})();

// READ
function makeElement(eleType, text, appendTo, className) {
    const element = document.createElement(eleType);
    element.innerText = text;
    element.className = className;
    appendTo.appendChild(element);
    return element;
}

function axiosDataElements() {
    axios.get(URL + '/read')
        .then(res => {
            bigOutput.innerText = '';
            res.data.forEach((task, i) => {
                const taskDiv = makeElement('div', '', bigOutput, 'card w-25 m-2 text-center');
                taskDiv.taskId = 'task' + i;
                taskDiv.addEventListener('click', function () {
                    window.location = './details.html?id=' + task.taskId;
                });
                makeElement('h2', task.taskDate, taskDiv, 'card-body card-header');
                makeElement('p', `@ ${task.taskTime}`, taskDiv, 'card-title');
                makeElement('p', `What: ${task.taskName}`, taskDiv, 'card-text');
                makeElement('p', `Where: ${task.taskLocation}`, taskDiv, 'card-text');
                makeElement('p', `Task ID: ${task.taskId}`, taskDiv, 'card-text');
            })
        })
        .catch(err => console.log(err));
}