import express from 'express';

import { loginUser, signupUser } from '../controllers/userController.js';
import { validateSignup } from '../middleware/validate.js';

const router = express.Router();

router.post("/signin", loginUser);

router.post("/signup", validateSignup, signupUser);

export default router;