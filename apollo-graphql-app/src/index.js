import { ApolloServer } from '@apollo/server';
import { startStandaloneServer } from '@apollo/server/standalone';
import { UsersAPI } from './datasource.js';


const typeDefs = `#graphql
  type User {
    id: ID
    firstName: String!
    lastName: String!
    fullName: String! # BE logic for generating full name
    phoneNumber: String # nullable
    aboutMe: String # large text
  }

  input UserUpdateInput {
    id: ID!
    firstName: String
    lastName: String
    phoneNumber: String
    aboutMe: String
  }

  type Query {
    users: [User]
  }

  type Mutation {
    updateUser1(userUpdateInput: UserUpdateInput): User
    updateUser2(userUpdateInput: UserUpdateInput): User
  }
`;

const resolvers = {
  Query: {
    users: async (_, __, { dataSources }) => {
      return dataSources.usersAPI.getAll();
    },
  },
  Mutation: {
    updateUser1: async (_, { userUpdateInput }, { dataSources }) => {
      return dataSources.usersAPI.partialUpdate(userUpdateInput);
    },
    updateUser2: async (_, { userUpdateInput }, { dataSources }) => {
      return dataSources.usersAPI.patchUser(userUpdateInput);
    }
  },
};

const server = new ApolloServer({
  typeDefs,
  resolvers,
});


const { url } = await startStandaloneServer(server, {
  context: async () => {
    return {
      dataSources: {
        usersAPI: new UsersAPI(),
      },
    };
  },
});

console.log(`🚀  Server ready at ${url}`);