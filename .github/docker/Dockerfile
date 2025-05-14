# Use Debian Bookworm for GLIBC 2.36 compatibility
FROM debian:bookworm

# Install Azul Zulu repository and dependencies
RUN apt-get update && apt-get install -y wget gnupg ca-certificates && \
    wget -qO - https://repos.azul.com/azul-repo.key | gpg --dearmor -o /usr/share/keyrings/azul.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/azul.gpg arch=arm64] https://repos.azul.com/zulu/deb stable main" > /etc/apt/sources.list.d/zulu.list

# Install ARM64-native build tools and Zulu JDK 8
RUN apt-get update && apt-get install -y \
    build-essential \
    mesa-common-dev libxxf86vm-dev \
    libxrandr-dev libx11-dev \
    zlib1g ccache gradle zulu-8-jdk  # ← Azul Zulu JDK 8

WORKDIR /build
COPY . .

# Build ARM64 natives for libGDX 1.9.5
RUN ./gradlew jnigen jnigenBuildLinux64 \
    --no-daemon
