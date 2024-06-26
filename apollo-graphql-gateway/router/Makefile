ROVER_URL = https://rover.apollo.dev/nix/latest
ROUTER_URL = https://router.apollo.dev/download/nix/latest

ROVER_INSTALL_SCRIPT = curl -sSL $(ROVER_URL) | sh
ROUTER_INSTALL_SCRIPT = curl -sSL $(ROUTER_URL) | sh

ROVER_PATH = ~/.rover/bin/rover
ROUTER_PATH = router

ROVER_CONFIG = rover.yaml
ROUTER_CONFIG = router.yaml
SUPERGRAPH_FILE = supergraph.graphql

# Download and install Rover if not present
$(ROVER_PATH):
	echo "Rover not found. Installing..."
	$(ROVER_INSTALL_SCRIPT)

# Download and install Router if not present
$(ROUTER_PATH):
	echo "Router not found. Installing..."
	$(ROUTER_INSTALL_SCRIPT)

# Run Rover and generate supergraph
$(SUPERGRAPH_FILE): $(ROVER_PATH)
	$(ROVER_PATH) supergraph compose --output $(SUPERGRAPH_FILE) --config $(ROVER_CONFIG)

# Run Router on the generated supergraph
run_router: $(SUPERGRAPH_FILE) $(ROUTER_PATH)
	./router --supergraph $(SUPERGRAPH_FILE) --config $(ROUTER_CONFIG)

serve: run_router

clean:
	rm -fv $(ROVER_PATH)
	rm -fv $(ROUTER_PATH)
	rm -fv $(SUPERGRAPH_FILE)

.PHONY: run_router serve
