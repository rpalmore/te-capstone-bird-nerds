import store from "../store/index.js";
import profileService from "./ProfileService.js";
import birdService from "./BirdService.js";
const cloudName = "dgupilxum";
const uploadPreset = "l1c4hxcn";
let profile = {
    username: store.state.user.username,
    profileImg: "",
    favoriteBird: store.state.profile.favoriteBird,
    mostCommonBird: store.state.profile.mostCommonBird,
    zipCode: store.state.profile.zipCode,
    skillLevel: store.state.profile.skillLevel
};

const myWidget = window.cloudinary.createUploadWidget(
    {
        cloudName: cloudName,
        uploadPreset: uploadPreset,
        sources: ["local", "url", "camera", "image_search", "google_drive", "unsplash", "facebook", "dropbox", "instagram"],
        googleApiKey: "<image_search_google_api_key>",
        showAdvancedOptions: true,
        cropping: true,
        multiple: false,
        defaultSource: "local",
        styles: {
            palette:
            {
                window: "#011627",
                windowBorder: "#2EC4b6",
                tabIcon: "#FF9F1C",
                menuIcons: "#FF9F1C",
                textDark: "#011627",
                textLight: "#FDFFFC",
                link: "#FDFFFC",
                action: "#FF9F1C",
                inactiveTabIcon: "#FDFFFC",
                error: "#E71D36",
                inProgress: "#FF9F1C",
                complete: "#20B832",
                sourceBg: "#011627"
            },
        }
    },
    (error, result) => {
        if (!error && result && result.event === "success" && store.state.birdPhoto === true) {
            let bird = store.state.birds.find(
                (b) => b.birdID == store.state.bird.birdId);
            bird.birdImg = result.info.secure_url;
            birdService.editBird(bird).then((response) => {
                if (response.status === 200) {
                    store.commit("EDIT_BIRD", bird);
                }
            }).catch((err) => {
                alert(err + " problem updating photo!");
            });
        } else if (!error && result && result.event === "success" && store.state.birdPhoto === false) {
            profile.profileImg = result.info.secure_url;
            profile.username = store.state.user.username;
            if (store.state.profile.favoriteBird === undefined) {
                profile.favoriteBird = null;
                profile.mostCommonBird = null;
                profile.zipCode = null;
                profile.skillLevel = null;
                profileService.updateProfile(profile).then((response) => {
                    if (response.status === 201) {
                        store.commit("SET_PROFILE", profile);
                    }
                })
            } else {
                profileService.editProfile(profile).then((response) => {
                    if (response.status === 200) {
                        store.commit("SET_PROFILE", profile);
                    }
                })
                    .catch((err) => {
                        alert(err + " problem updating profile!");
                    });
            }
        }
    }
);

export default {
    myWidget
}