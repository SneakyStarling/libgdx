# Use Debian Bookworm for GLIBC 2.36 compatibility
FROM debian:bookworm

# Install ARM64-native build tools and dependencies (JDK 8 for libGDX 1.9.5)
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
