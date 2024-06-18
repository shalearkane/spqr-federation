ROVER_URL = https://rover.apollo.dev/nix/latest
ROUTER_URL = https://router.apollo.dev/download/nix/latest
ROVER_INSTALL_SCRIPT = curl -sSL $(ROVER_URL) | sh
ROUTER_INSTALL_SCRIPT = curl -sSL $(ROUTER_URL) | sh
ROVER_PATH = ~/.rover/bin/rover
CONFIG_FILE = supergraph-config.yaml
SUPERGRAPH_FILE = supergraph.graphql

# Download and install Rover if not present
$(ROVER_PATH):
	echo "Rover not found. Installing..."
	$(ROVER_INSTALL_SCRIPT)

# Run Rover and generate supergraph
generate_supergraph: $(ROVER_PATH)
	$(ROVER_PATH) supergraph compose --output $(SUPERGRAPH_FILE) --config $(CONFIG_FILE)

# Run Router on the generated supergraph
run_router: generate_supergraph
	router --supergraph $(SUPERGRAPH_FILE)

serve: run_router

clean:
	rm -fv $(ROVER_PATH)
	rm -fv q$(SUPERGRAPH_FILE)

.PHONY: generate_supergraph run_router all
