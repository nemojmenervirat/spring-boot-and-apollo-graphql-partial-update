import { RESTDataSource } from '@apollo/datasource-rest';

export class UsersAPI extends RESTDataSource {
  baseURL = 'http://localhost:8080/';

  async getAll() {
    return await this.get('users');
  }

  async partialUpdate(userUpdateInput) {
    return await this.put('users', { body: userUpdateInput });
  }

  async patchUser(userUpdateInput) {
    let jsonPatch = [];
    this.appendField(jsonPatch, "firstName", userUpdateInput.firstName);
    this.appendField(jsonPatch, "lastName", userUpdateInput.lastName);
    this.appendField(jsonPatch, "phoneNumber", userUpdateInput.phoneNumber);
    return await this.patch(`users/${userUpdateInput.id}`, { body: jsonPatch });
  }

  appendField(jsonPatch, fieldName, fieldValue) {
    if (fieldValue !== undefined) {
      if (fieldValue != null) {
        jsonPatch.push({ op: "replace", path: `/${fieldName}`, value: fieldValue });
      } else {
        jsonPatch.push({ op: "remove", path: `/${fieldName}` });
      }
    }
  }

}