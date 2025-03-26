const canvas = document.getElementById("canvas");
        const draw = canvas.getContext("2d");  
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;

        const G = 6.67430e-11;
        let SCALE = 1.5e9;
        let TIMESTEP = 1e5;
        let zoomFactor = 1;
        let offsetX = 0, offsetY = 0;
        let dragging = false, lastX, lastY;

        const speedSlider = document.getElementById("speedSlider");
        const speedValue = document.getElementById("speedValue");

        speedSlider.addEventListener("input", () => {
            TIMESTEP = parseFloat(speedSlider.value) * 1e5;
            speedValue.textContent = speedSlider.value;
        });
        // class constructor
        class Planet {
            constructor(x, y, mass, radius, vx, vy, color) {
                this.x = x;
                this.y = y;
                this.mass = mass;
                this.radius = radius;
                this.vx = vx;
                this.vy = vy;
                this.color = color;
                this.trail = [];  
            }

            applyGravity(other) {
                let dx = other.x - this.x;
                let dy = other.y - this.y;
                let distance = Math.sqrt(dx * dx + dy * dy) + 1e-10;

                let force = (G * this.mass * other.mass) / (distance * distance);
                let fx = force * (dx / distance);
                let fy = force * (dy / distance);

                let ax = fx / this.mass;
                let ay = fy / this.mass;

                this.vx += ax * TIMESTEP;
                this.vy += ay * TIMESTEP;
            }

            updatePosition() {
                this.trail.push({ x: this.x, y: this.y });
                if (this.trail.length > 50) this.trail.shift(); 

                this.x += this.vx * TIMESTEP;
                this.y += this.vy * TIMESTEP;
            }

            drawPlanet() {
                draw.strokeStyle = this.color;
                draw.beginPath();
                this.trail.forEach((pos, index) => {
                    draw.globalAlpha = index / this.trail.length; 
                    draw.lineTo(pos.x / SCALE + canvas.width / 2 + offsetX, pos.y / SCALE + canvas.height / 2 + offsetY);
                });
                draw.stroke();
                draw.globalAlpha = 1.0;

                draw.fillStyle = this.color;
                draw.beginPath();
                draw.arc(this.x / SCALE + canvas.width / 2 + offsetX, this.y / SCALE + canvas.height / 2 + offsetY, this.radius / zoomFactor, 0, Math.PI * 2);
                draw.fill();
            }
        }

        let planets = [
            new Planet(0, 0, 1.989e30, 20, 0, 0, "yellow"),   
            new Planet(5.46e10, 0, 3.3e23, 5, 0, 47000, "pink"),  
            new Planet(1.08e11, 0, 4.87e24, 6, 0, 35000, "gray"),   
            new Planet(1.496e11, 0, 5.972e24, 10, 0, 29780, "green"),  
            new Planet(2.279e11, 0, 6.39e23, 8, 0, 24130, "red"),   
            new Planet(7.785e11, 0, 1.898e27, 15, 0, 13070, "orange"),  
            new Planet(1.433e12, 0, 5.683e26, 12, 0, 9690, "gold"),  
            new Planet(2.877e12, 0, 8.681e25, 10, 0, 6810, "lightblue"),  
            new Planet(4.503e12, 0, 1.024e26, 10, 0, 5430, "blue")  
        ];

        function animate() {
            draw.clearRect(0, 0, canvas.width, canvas.height);

            for (let i = 0; i < planets.length; i++) {
                for (let j = 0; j < planets.length; j++) {
                    if (i !== j) {
                        planets[i].applyGravity(planets[j]);
                    }
                }
            }

            planets.forEach(planet => {
                planet.updatePosition();
                planet.drawPlanet();  
            });

            requestAnimationFrame(animate);  
        }

        canvas.addEventListener("wheel", (event) => {
            event.preventDefault();
            let zoomAmount = event.deltaY * 0.001;
            let newZoomFactor = zoomFactor * (1 + zoomAmount);

            if (newZoomFactor > 0.2 && newZoomFactor < 5) {
                zoomFactor = newZoomFactor;
                SCALE *= (1 + zoomAmount);
            }
        });

        canvas.addEventListener("mousedown", (event) => {
            dragging = true;
            lastX = event.clientX;
            lastY = event.clientY;
        });

        canvas.addEventListener("mousemove", (event) => {
            if (dragging) {
                let dx = event.clientX - lastX;
                let dy = event.clientY - lastY;

                offsetX += dx;
                offsetY += dy;

                lastX = event.clientX;
                lastY = event.clientY;
            }
        });

        canvas.addEventListener("mouseup", () => dragging = false);
        canvas.addEventListener("mouseleave", () => dragging = false);

        animate(); 
