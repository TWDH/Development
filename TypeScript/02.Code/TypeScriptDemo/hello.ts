function sayHello(person: string) {
    return 'Hello, ' + person;
}

let user = 'Tom';
console.log(sayHello(user));

function sayHello2(person: string) {
    return 'Hello, ' + person;
}

let user2 = [0, 1, 2];
console.log(sayHello(user));

let myFavoriteNumber: any = 'seven';
myFavoriteNumber = 7;


let anyThing: any = 'hello';
console.log(anyThing.myName);
console.log(anyThing.myName.firstName);

// let myFavoriteNumber2 = 'seven';
// myFavoriteNumber2 = 7;

let temp : string | number;
temp = 'seven';
console.log(temp.length)

temp = 7;
console.log(temp.length)