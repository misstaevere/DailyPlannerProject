'use strict;'
const bigOutput = document.getElementById('bigOutput');
const URL = '/task';

const params = new URLSearchParams(window.location.search);
console.log('id: ', params.get('taskId'));

(function () {
    const params = new URLSearchParams(window.location.search);
    axios.get(URL + '/read/' + params.get('id')).then(res => { // get request for a specific task
        const task = res.data; // extract the duck object from the response
        const taskDiv = makeElement('div', '', bigOutput, 'card w-25 m-2 text-center');
        makeElement('h2', task.taskDate, taskDiv, 'card-body card-header');
        makeElement('h2', `@ ${task.taskTime}`, taskDiv, 'card-title');
        makeElement('p', `What: ${task.taskName}`, taskDiv, 'card-text');
        makeElement('p', `Where: ${task.taskLocation}`, taskDiv, 'card-text');
        makeElement('p', `Task ID: ${task.taskId}`, taskDiv, 'card-text');
    }).catch(err => console.error(err));
    // UPDATE
    document.getElementById('editForm').addEventListener('submit', function (event) {
        event.preventDefault();
        const data = {};
        data.taskDate = this.editDate.value;
        data.taskTime = this.editTime.value;
        data.taskName = this.editName.value;
        data.taskLocation = this.editLocation.value;
        axios.put(URL + '/update/' + params.get('id'), data)
            .then(alert("You have successfully updated the task!"))
            .then(res => console.log(res))
            .catch(err => console.log(err));
    });
    // DELETE
    document.getElementById('delete_button').addEventListener('click', function () {
        axios.delete(URL + '/delete/' + document.getElementById('delete_input').value)
            .then(res => alert("You have successfully deleted the task with the ID of " + document.getElementById('delete_input').value + "!"))
            .catch(err => console.log(err));
    });
})();

function makeElement(eleType, text, appendTo, className) {
    const element = document.createElement(eleType);
    element.innerText = text;
    element.className = className;
    appendTo.appendChild(element);
    return element;
}
