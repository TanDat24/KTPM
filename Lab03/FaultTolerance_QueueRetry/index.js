class TaskQueue {
    constructor() {
      this.queue = [];
    }
  
    addTask(task) {
      this.queue.push(task);
    }
  
    async processQueue() {
      while (this.queue.length > 0) {
        const task = this.queue.shift();
        try {
          await this.executeTaskWithRetry(task);
        } catch (error) {
          console.error(`Task failed after retries: ${error.message}`);
        }
      }
    }
  
    async executeTaskWithRetry(task, retries = 3, delay = 1000) {
      let attempt = 0;
      while (attempt < retries) {
        try {
          console.log(`Attempting task: ${task.name}, attempt #${attempt + 1}`);
          await task();
          console.log(`Task "${task.name}" completed successfully.`);
          return; 
        } catch (error) {
          console.error(`Error executing task "${task.name}": ${error.message}`);
          attempt++;
          if (attempt < retries) {
            console.log(`Retrying in ${delay}ms...`);
            await this.sleep(delay);
          }
        }
      }
      throw new Error(`Task "${task.name}" failed after ${retries} retries.`);
    }
  
    sleep(ms) {
      return new Promise(resolve => setTimeout(resolve, ms));
    }
  }
  
  function createTask(name, shouldFail) {
    return async () => {
      if (shouldFail) {
        throw new Error(`Task "${name}" failed!`);
      }
      console.log(`Task "${name}" completed successfully.`);
    };
  }
  
  const taskQueue = new TaskQueue();
  
  taskQueue.addTask(createTask('Task 1', true));
  taskQueue.addTask(createTask('Task 2', false));
  taskQueue.addTask(createTask('Task 3', true)); 
  
  taskQueue.processQueue();
  