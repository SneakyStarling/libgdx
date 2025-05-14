FROM debian:bookworm

# Install dependencies and add OpenJDK 8 repository
RUN apt-get update && apt-get install -y wget && \
    wget -qO - https://deb.tensorwerk.com/repo.gpg.key | gpg --dearmor > /usr/share/keyrings/tensorwerk-repo.gpg && \
    echo "deb [arch=arm64 signed-by=/usr/share/keyrings/tensorwerk-repo.gpg] https://deb.tensorwerk.com/debian all main" > /etc/apt/sources.list.d/tensorwerk.list

# Install ARM64-native build tools and dependencies
RUN apt-get update && apt-get install -y \
    build-essential \
    mesa-common-dev libxxf86vm-dev \
    libxrandr-dev libx11-dev \
    zlib1g ccache gradle openjdk-8-jdk

WORKDIR /build
COPY . .

# Build ARM64 natives for older libGDX versions
RUN ./gradlew jnigen jnigenBuildLinux64 \
    --no-daemon
