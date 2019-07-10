FROM node:8.12.0

# Create app directory
RUN mkdir /usr/src/ui
WORKDIR /usr/src/ui

COPY ui/package*.json ./
RUN npm install

COPY ./ui .
RUN npm run build 

# Create app directory
RUN mkdir /usr/src/app
WORKDIR /usr/src/app

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY middle-tier/package*.json ./

## Set environment to "development" by default
ENV NODE_ENV development

RUN npm install
# If you are building your code for production
#RUN npm install --only=production

#RUN npm i -g cross-env

# Bundle app source
COPY ./middle-tier .

EXPOSE 9003
CMD [ "npm", "start" ]
