
export const getUser = () => {
    return fetch("http://localhost:8080/users/" + localStorage.getItem("user-id"))
}

export function setUser(body: { userName: string}) {
    return fetch("http://localhost:8080/registers", {
        method: "POST",
        headers: {
            'Accept': 'application/json, text/plain, */*',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(body)
    })
}