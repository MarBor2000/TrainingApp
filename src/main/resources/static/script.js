const apiBaseUrl = "http://localhost:8080/users";

async function fetchUsers() {
    const response = await fetch(apiBaseUrl);
    const users = await response.json();
    displayUsers(users);
}

async function fetchUsers() {
    const response = await fetch(apiBaseUrl);
    const users = await response.json();
    displayUsers(users);
}

function displayUsers(users) {
    const userList = document.getElementById("userList");
    userList.innerHTML = "";

    users.forEach(user => {
        const listItem = document.createElement("li");
        listItem.className = "list-group-item";
        listItem.innerHTML = `
            <span>${user.firstName} ${user.lastName}</span>
            <div>
                <button class="btn btn-sm btn-info" onclick="selectUser('${user.id}')">View Plans</button>
                <button class="btn btn-sm btn-danger" onclick="deleteUser('${user.id}')">Delete</button>
            </div>
        `;
        userList.appendChild(listItem);
    });
}

document.getElementById("userForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;

    await fetch(apiBaseUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ firstName, lastName }),
    });

    e.target.reset();
    fetchUsers();
});

async function selectUser(userId) {
    document.getElementById("selectedUserId").value = userId;
    document.getElementById("trainingPlanForm").classList.remove("d-none");
    document.getElementById("trainingPlans").classList.remove("d-none");

    const response = await fetch(`${apiBaseUrl}/${userId}/plans`);
    const plans = await response.json();
    displayTrainingPlans(plans);
}

function displayTrainingPlans(plans) {
    const planList = document.getElementById("planList");
    planList.innerHTML = "";

    plans.forEach(plan => {
        const listItem = document.createElement("li");
        listItem.className = "list-group-item";
        listItem.innerHTML = `
            <h5>${plan.goal} (${plan.daysPerWeek} days/week)</h5>
            <ul>
                ${plan.exercises.map(exercise => `
                    <li>${exercise.name} - ${exercise.sets} sets, ${exercise.reps} reps, tempo: ${exercise.tempo}, rest: ${exercise.rest}</li>
                `).join("")}
            </ul>
        `;
        planList.appendChild(listItem);
    });
}

