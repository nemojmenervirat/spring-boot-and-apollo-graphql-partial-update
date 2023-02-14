# Spring Boot & Apollo GraphQL - Partial Update

Example how partial update can be implemented if we have Spring Boot REST datasource behind Apollo GraphQL.
More details can be found <a href="https://medium.com/@milan-djurica/spring-boot-apollo-graphql-partial-update-6fec9c3496e4">here</a>

<b>Start Spring Boot App</b>

Go into `/spring-boot-app` and run `gradlew bootRun`

<b>Start Apollo GraphQL App</b>

Go into `/apollo-graphql-app` and run `npm install && npm start`

<b>Go to Apollo Studio</b>

Open browser and go to `http://localhost:4000`

<b>Execute partial update mutation</b>

Update first name to some value and phone number to null:

```
mutation UpdateUser1($updateUser1UserUpdateInput: UserUpdateInput) {
  updateUser1(userUpdateInput: $updateUser1UserUpdateInput) {
    id
    firstName
    lastName
    fullName
    phoneNumber
    aboutMe
  }
}

{
  "updateUser1UserUpdateInput": {
    "id": "5ec4b434-e43e-438d-b93c-025285178d6f",
    "firstName": "Milannn",
    "phoneNumber": null
  }
}
```

Only first name & phone number where updated:

```
{
  "data": {
    "updateUser1": {
      "id": "5ec4b434-e43e-438d-b93c-025285178d6f",
      "firstName": "Milannn",
      "lastName": "Djurica",
      "fullName": "Milan Djurica",
      "phoneNumber": null,
      "aboutMe": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    }
  }
}
```