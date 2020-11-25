import React from "react";

const roomList = [
    {
        name: 'Test',
        lifeTime: '22-10-2020 15:30',
        publicId: 'aba',
        users: 15,
        maxSize: 20,
        creator: "User1",
        isAlreadyLogged: true
    },
    {
        name: 'Test1',
        lifeTime: '22-10-2020 15:30',
        publicId: 'aab',
        users: 15,
        maxSize: null,
        creator: "User12",
        isAlreadyLogged: false
    },
    {
        name: 'Test2',
        lifeTime: '22-10-2020 15:30',
        publicId: 'aa',
        users: 15,
        maxSize: 20,
        creator: "User13",
        isAlreadyLogged: true
    }
]


export function Main() {
    return (
        <section>
            <article>
                <h2>Pokoje które używałeś</h2>
                { roomList.filter(data => data.isAlreadyLogged).map(data => <Room key={data.publicId} room={data}/>)}
            </article>
            <article>
                <h2>Pozostałe pokoje</h2>
                { roomList.filter(data => !data.isAlreadyLogged).map(data => <Room key={data.publicId}  room={data}/>)}
            </article>
        </section>

    )
}

const Room = (props) => {
    return (
        <div>
            {props.room.name} {props.room.users}/{ props.room.maxSize ? props.room.maxSize : '∞'}
            <button type="button">Dołącz</button>
        </div>
    )
}