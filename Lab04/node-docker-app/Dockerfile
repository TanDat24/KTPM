# Base image
FROM node:18

# Thư mục làm việc trong container
WORKDIR /app

# Copy file vào container
COPY package.json .

# Cài dependency (nếu có)
RUN npm install

# Copy toàn bộ source code
COPY . .

# Mở port 3000
EXPOSE 3000

# Chạy ứng dụng
CMD ["npm", "start"]