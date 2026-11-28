(() => {
    "use strict";

    const AGENT_ORDER = ["intakeAgent", "guaranteeAgent", "outputAgent"];

    const AGENT_EMOJI = {
        intakeAgent: "📥",
        guaranteeAgent: "🛡️",
        outputAgent: "✉️",
    };

    const BADGE_EMOJI = {
        accepted: "✅",
        rejected: "❌",
        "information-missing": "⚠️",
        error: "⚠️",
        running: "⏳",
        queued: "⏳",
    };

    const GENERIC_AVATAR = `
        <svg viewBox="0 0 64 64" xmlns="http://www.w3.org/2000/svg">
            <circle cx="32" cy="32" r="32" fill="#ececf0"/>
            <circle cx="32" cy="25" r="11" fill="#b9b9c0"/>
            <path d="M13 64c2-12 9-18 19-18s17 6 19 18" fill="#b9b9c0"/>
        </svg>`;

    const CASE_PROFILES = {
        "case-01-homer": {
            tagline: "Safety inspector at the nuclear plant",
            avatar: `
                <svg viewBox="0 0 64 64" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="32" cy="32" r="32" fill="#ffe7a8"/>
                    <ellipse cx="32" cy="37" rx="16" ry="18" fill="#f7d64a"/>
                    <path d="M18 24c2-7 8-10 14-10s12 3 14 10" fill="none" stroke="#8a5a2b" stroke-width="5" stroke-linecap="round"/>
                    <circle cx="16" cy="36" r="3.5" fill="#f7d64a"/>
                    <circle cx="48" cy="36" r="3.5" fill="#f7d64a"/>
                    <circle cx="26" cy="34" r="2.4" fill="#242424"/>
                    <circle cx="38" cy="34" r="2.4" fill="#242424"/>
                    <ellipse cx="32" cy="41" rx="3.2" ry="2.2" fill="#e0b63c"/>
                    <path d="M23 46c4 4 14 4 18 0" fill="none" stroke="#8a5a2b" stroke-width="2" stroke-linecap="round"/>
                    <circle cx="24" cy="45" r="1" fill="#8a5a2b"/>
                    <circle cx="28" cy="48" r="1" fill="#8a5a2b"/>
                    <circle cx="36" cy="48" r="1" fill="#8a5a2b"/>
                    <circle cx="40" cy="45" r="1" fill="#8a5a2b"/>
                    <path d="M14 64c2-9 9-14 18-14s16 5 18 14" fill="#ffffff"/>
                </svg>`,
        },
        "case-02-bart": {
            tagline: "Skateboarder and professional underachiever",
            avatar: `
                <svg viewBox="0 0 64 64" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="32" cy="32" r="32" fill="#ffe6a3"/>
                    <path d="M17 27l3-11 4 8 3-12 4 10 3-11 4 11 3-10 4 12 2-8 1 12" fill="#f7d64a" stroke="#d9b22b" stroke-width="1.5" stroke-linejoin="round"/>
                    <ellipse cx="32" cy="37" rx="16" ry="17" fill="#f7d64a"/>
                    <circle cx="16" cy="36" r="3.5" fill="#f7d64a"/>
                    <circle cx="48" cy="36" r="3.5" fill="#f7d64a"/>
                    <circle cx="26" cy="34" r="2.4" fill="#242424"/>
                    <circle cx="38" cy="34" r="2.4" fill="#242424"/>
                    <ellipse cx="32" cy="41" rx="3" ry="2" fill="#e0b63c"/>
                    <path d="M25 47c4 2 10 2 14-1" fill="none" stroke="#6b3f1d" stroke-width="2" stroke-linecap="round"/>
                    <path d="M14 64c2-9 9-14 18-14s16 5 18 14" fill="#e23b3b"/>
                </svg>`,
        },
        "case-03-marge": {
            tagline: "Homemaker and voice of reason",
            avatar: `
                <svg viewBox="0 0 64 64" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="32" cy="32" r="32" fill="#d9ecff"/>
                    <path d="M17 34c-2-20 5-28 15-28s17 8 15 28z" fill="#2f6fd0"/>
                    <ellipse cx="32" cy="38" rx="15" ry="16" fill="#f7d64a"/>
                    <path d="M17 34c2 6 5 9 7 10M47 34c-2 6-5 9-7 10" fill="none" stroke="#2f6fd0" stroke-width="4" stroke-linecap="round"/>
                    <circle cx="26" cy="36" r="2.3" fill="#242424"/>
                    <circle cx="38" cy="36" r="2.3" fill="#242424"/>
                    <ellipse cx="32" cy="42" rx="3" ry="2" fill="#e0b63c"/>
                    <path d="M26 48c3 2 9 2 12 0" fill="none" stroke="#8a5a2b" stroke-width="2" stroke-linecap="round"/>
                    <path d="M14 64c2-9 9-14 18-14s16 5 18 14" fill="#3ba55d"/>
                    <circle cx="27" cy="52" r="1.8" fill="#e23b3b"/>
                    <circle cx="32" cy="54" r="1.8" fill="#e23b3b"/>
                    <circle cx="37" cy="52" r="1.8" fill="#e23b3b"/>
                </svg>`,
        },
        "case-04-lisa": {
            tagline: "Student, saxophonist, vegetarian",
            avatar: `
                <svg viewBox="0 0 64 64" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="32" cy="32" r="32" fill="#ffe3ee"/>
                    <path d="M17 31l2-13 4 8 3-12 4 10 4-11 4 11 3-10 4 12 2-9 2 14z" fill="#f7d64a" stroke="#d9b22b" stroke-width="1.5" stroke-linejoin="round"/>
                    <ellipse cx="32" cy="38" rx="15" ry="16" fill="#f7d64a"/>
                    <circle cx="26" cy="36" r="2.3" fill="#242424"/>
                    <circle cx="38" cy="36" r="2.3" fill="#242424"/>
                    <ellipse cx="32" cy="42" rx="3" ry="2" fill="#e0b63c"/>
                    <path d="M27 48c3 2 7 2 10 0" fill="none" stroke="#8a5a2b" stroke-width="2" stroke-linecap="round"/>
                    <path d="M14 64c2-9 9-14 18-14s16 5 18 14" fill="#e23b3b"/>
                    <circle cx="27" cy="52" r="1.6" fill="#ffffff"/>
                    <circle cx="32" cy="54" r="1.6" fill="#ffffff"/>
                    <circle cx="37" cy="52" r="1.6" fill="#ffffff"/>
                </svg>`,
        },
        "case-05-burns": {
            tagline: "Owner of the Springfield Nuclear Power Plant",
            avatar: `
                <svg viewBox="0 0 64 64" xmlns="http://www.w3.org/2000/svg">
                    <circle cx="32" cy="32" r="32" fill="#e6f0dc"/>
                    <path d="M15 30c0-4 3-6 6-6M49 30c0-4-3-6-6-6" fill="none" stroke="#b9b9b9" stroke-width="4" stroke-linecap="round"/>
                    <ellipse cx="32" cy="37" rx="15" ry="17" fill="#d9de9b"/>
                    <ellipse cx="32" cy="27" rx="10" ry="6" fill="#e6ebae"/>
                    <circle cx="26" cy="34" r="2.3" fill="#242424"/>
                    <circle cx="38" cy="34" r="2.3" fill="#242424"/>
                    <ellipse cx="32" cy="41" rx="3.2" ry="2.4" fill="#b9be7d"/>
                    <path d="M25 48c4-3 10-3 14 0" fill="none" stroke="#6e6e4f" stroke-width="2" stroke-linecap="round"/>
                    <path d="M12 64c2-10 10-15 20-15s18 5 20 15" fill="#2b2b2b"/>
                    <path d="M28 50l4 6 4-6" fill="#f5f5f5"/>
                </svg>`,
        },
    };

    function profileFor(caseId) {
        return CASE_PROFILES[caseId] || { tagline: "", avatar: GENERIC_AVATAR };
    }

    const state = {
        cases: [],
        selected: null,
        runId: null,
        running: false,
        timer: null,
    };

    const dom = {
        caseList: document.getElementById("case-list"),
        caseCount: document.getElementById("case-count"),
        caseAvatar: document.getElementById("case-avatar"),
        caseTitle: document.getElementById("case-title"),
        caseSubtitle: document.getElementById("case-subtitle"),
        caseContent: document.getElementById("case-content"),
        runButton: document.getElementById("run-button"),
        statusBadge: document.getElementById("status-badge"),
        agents: document.getElementById("agents"),
        resultMeta: document.getElementById("result-meta"),
        resultText: document.getElementById("result-text"),
    };

    async function api(path, options) {
        const response = await fetch(path, options);
        if (!response.ok) {
            let message = response.status + " " + response.statusText;
            try {
                const body = await response.json();
                if (body && body.message) message = body.message;
            } catch (ignored) {
                // keep the status line
            }
            throw new Error(message);
        }
        if (response.status === 204) return null;
        return response.json();
    }

    function setBadge(label, modifier) {
        const emoji = BADGE_EMOJI[modifier];
        dom.statusBadge.textContent = (emoji ? emoji + " " : "") + label;
        dom.statusBadge.className = "badge" + (modifier ? " " + modifier : "");
    }

    function addMeta(label, value) {
        const item = document.createElement("span");
        const strong = document.createElement("strong");
        strong.textContent = label + ": ";
        item.append(strong, document.createTextNode(value));
        dom.resultMeta.append(item);
    }

    function updateSelectedTile() {
        dom.caseList.querySelectorAll(".case-tile").forEach((tile) => {
            tile.classList.toggle("selected", tile.dataset.caseId === state.selected);
        });
    }

    function renderCases() {
        dom.caseList.innerHTML = "";
        state.cases.forEach((testCase) => {
            const profile = profileFor(testCase.id);

            const tile = document.createElement("button");
            tile.type = "button";
            tile.className = "case-tile";
            tile.dataset.caseId = testCase.id;

            const avatar = document.createElement("span");
            avatar.className = "case-avatar";
            avatar.setAttribute("aria-hidden", "true");
            avatar.innerHTML = profile.avatar;

            const textBlock = document.createElement("span");
            textBlock.className = "case-text-block";

            const title = document.createElement("span");
            title.className = "case-title";
            title.textContent = testCase.title;

            const id = document.createElement("span");
            id.className = "case-id";
            id.textContent = testCase.id;

            textBlock.append(title, id);
            tile.append(avatar, textBlock);
            tile.addEventListener("click", () => selectCase(testCase.id));
            dom.caseList.append(tile);
        });
        dom.caseCount.textContent = state.cases.length + " cases";
        updateSelectedTile();
    }

    function renderAgents(agents, currentAgent) {
        const byName = new Map(agents.map((agent) => [agent.name, agent]));
        const ordered = AGENT_ORDER.map((name) => byName.get(name) || { name, state: "PENDING" });
        const extras = agents.filter((agent) => !AGENT_ORDER.includes(agent.name));

        dom.agents.innerHTML = "";
        ordered.concat(extras).forEach((agent) => {
            const pill = document.createElement("span");
            pill.className = "agent " + String(agent.state || "PENDING").toLowerCase();
            if (agent.name === currentAgent) pill.classList.add("current");
            if (agent.output) pill.title = agent.output;
            if (agent.error) pill.title = agent.error;

            const dot = document.createElement("span");
            dot.className = "dot";

            const emoji = document.createElement("span");
            emoji.className = "agent-emoji";
            emoji.textContent = AGENT_EMOJI[agent.name] || "🤖";

            const label = document.createElement("span");
            label.className = "agent-name";
            label.textContent = agent.name;

            const phase = document.createElement("span");
            phase.className = "agent-state";
            phase.textContent = String(agent.state || "pending").toLowerCase();

            pill.append(dot, emoji, label, phase);
            dom.agents.append(pill);
        });
    }

    function resetResult() {
        setBadge("IDLE", "");
        dom.resultMeta.innerHTML = "";
        dom.resultText.classList.add("empty");
        dom.resultText.textContent = "Run the flow to see the output agent's reply.";
        renderAgents(AGENT_ORDER.map((name) => ({ name, state: "PENDING" })), null);
    }

    async function selectCase(caseId) {
        if (state.running || caseId === state.selected) return;

        try {
            const detail = await api("/api/mapple/cases/" + encodeURIComponent(caseId));
            const profile = profileFor(detail.id);
            state.selected = caseId;
            dom.caseAvatar.innerHTML = profile.avatar;
            dom.caseTitle.textContent = detail.title;
            dom.caseSubtitle.textContent = profile.tagline
                ? detail.id + " · " + profile.tagline
                : detail.id;
            dom.caseContent.textContent = detail.content;
            dom.caseContent.classList.remove("empty");
            dom.runButton.disabled = false;
            updateSelectedTile();
            resetResult();
        } catch (error) {
            dom.caseTitle.textContent = "Could not load test case";
            dom.caseSubtitle.textContent = error.message;
        }
    }

    function renderProgress(run) {
        renderAgents(run.agents || [], run.currentAgent);

        if (run.state === "QUEUED") {
            setBadge("QUEUED", "queued");
            dom.resultMeta.innerHTML = "";
            dom.resultText.classList.add("empty");
            dom.resultText.textContent = "Waiting for a free worker…";
            return;
        }

        setBadge(run.currentAgent ? "RUNNING · " + run.currentAgent : "RUNNING", "running");
        dom.resultMeta.innerHTML = "";

        const completed = (run.agents || [])
            .filter((agent) => agent.state === "DONE")
            .map((agent) => agent.name);
        if (completed.length) addMeta("Completed", completed.join(" → "));

        dom.resultText.classList.add("empty");
        dom.resultText.textContent = run.currentAgent
            ? run.currentAgent + " is working…"
            : "Agent flow running…";
    }

    function finishRun(run) {
        state.running = false;
        if (state.timer) {
            clearTimeout(state.timer);
            state.timer = null;
        }
        dom.runButton.disabled = !state.selected;
        dom.resultMeta.innerHTML = "";
        renderAgents(run.agents || [], null);

        if (run.state === "DONE") {
            const status = run.status || "UNKNOWN";
            setBadge(status, status.toLowerCase().replace(/_/g, "-"));
            if (run.reason) addMeta("Reason", run.reason);
            addMeta("Run", run.runId.slice(0, 8));
            dom.resultText.classList.remove("empty");
            dom.resultText.textContent = run.result || "(no output)";
            return;
        }

        setBadge("ERROR", "error");
        addMeta("Run", run.runId.slice(0, 8));
        dom.resultText.classList.remove("empty");
        dom.resultText.textContent = run.error || "The run failed.";
    }

    async function poll() {
        if (!state.runId) return;

        try {
            const run = await api("/api/mapple/runs/" + encodeURIComponent(state.runId));
            if (run.state === "DONE" || run.state === "ERROR") {
                finishRun(run);
                return;
            }
            renderProgress(run);
            state.timer = setTimeout(poll, 400);
        } catch (error) {
            state.running = false;
            dom.runButton.disabled = !state.selected;
            setBadge("ERROR", "error");
            dom.resultText.classList.remove("empty");
            dom.resultText.textContent = error.message;
        }
    }

    async function runFlow() {
        if (!state.selected || state.running) return;

        state.running = true;
        state.runId = null;
        if (state.timer) {
            clearTimeout(state.timer);
            state.timer = null;
        }

        dom.runButton.disabled = true;
        dom.resultMeta.innerHTML = "";
        dom.resultText.classList.remove("empty");
        dom.resultText.textContent = "Starting agent flow…";
        setBadge("RUNNING", "running");
        renderAgents(AGENT_ORDER.map((name) => ({ name, state: "PENDING" })), null);

        try {
            const started = await api(
                "/api/mapple/cases/" + encodeURIComponent(state.selected) + "/run",
                { method: "POST" }
            );
            state.runId = started.runId;
            poll();
        } catch (error) {
            state.running = false;
            dom.runButton.disabled = !state.selected;
            setBadge("ERROR", "error");
            dom.resultText.textContent = error.message;
        }
    }

    async function init() {
        dom.runButton.addEventListener("click", runFlow);
        dom.caseAvatar.innerHTML = GENERIC_AVATAR;
        resetResult();

        try {
            state.cases = await api("/api/mapple/cases");
            renderCases();
            if (state.cases.length) await selectCase(state.cases[0].id);
        } catch (error) {
            dom.caseTitle.textContent = "Could not load test cases";
            dom.caseSubtitle.textContent = error.message;
        }
    }

    init();
})();
