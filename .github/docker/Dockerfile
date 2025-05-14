FROM debian:bookworm

# Install Azul Zulu JDK 8 for ARM64
RUN apt-get update && apt-get install -y wget gnupg ca-certificates && \
    wget -qO - https://repos.azul.com/azul-repo.key | gpg --dearmor -o /usr/share/keyrings/azul.gpg && \
    echo "deb [signed-by=/usr/share/keyrings/azul.gpg arch=arm64] https://repos.azul.com/zulu/deb stable main" > /etc/apt/sources.list.d/zulu.list && \
    apt-get update && apt-get install -y zulu8-jdk

# Install build tools
RUN apt-get install -y \
    build-essential \
    mesa-common-dev libxxf86vm-dev \
    libxrandr-dev libx11-dev \
    zlib1g ccache

WORKDIR /build
COPY . .

# Force Gradle 4.10.3 for libGDX 1.9.5 compatibility
RUN wget https://services.gradle.org/distributions/gradle-4.10.3-bin.zip && \
    unzip gradle-4.10.3-bin.zip && \
    export PATH="$PATH:/build/gradle-4.10.3/bin"

# Remove Android modules from settings.gradle
RUN sed -i '/android/d' settings.gradle && \
    sed -i '/gdx-tests-android/d' settings.gradle

# Set execute permissions and build
RUN chmod +x gradlew && \
    ./gradlew --stop && \
    ./gradlew jnigen jnigenBuildLinux64 \
      --no-daemon \
      --refresh-dependencies \
      -Dorg.gradle.java.home=/usr/lib/jvm/zulu8-ca-aarch64
