# Chọn Node.js LTS
FROM node:20-alpine

# Set working directory
WORKDIR /app

# Copy package.json & package-lock.json
COPY package*.json ./

# Cài dependencies
RUN npm install

# Copy toàn bộ source code
COPY . .

# Expose port server
EXPOSE 5000

# Command chạy server
CMD ["node", "server.js"]