
export const getUser = () => {
    return fetch("http://localhost:8080/users/" + localStorage.getItem("user-id"))
}