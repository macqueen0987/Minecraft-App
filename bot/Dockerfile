FROM python:3.12

RUN apt-get update && apt-get upgrade -y

# Set the working directory
WORKDIR /app
# Copy the requirements file
COPY requirements.txt .
# Install the dependencies
RUN pip install --no-cache-dir -r requirements.txt