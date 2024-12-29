import AuthForm from "@/app/components/AuthForm";
import { AuthFormProps, AuthProps } from "@/app/types/interfaces";

const formFields: AuthFormProps[] = [
  { id: "email", type: "email", placeholder: "Email" },
  { id: "password", type: "password", placeholder: "Password" },
];

const data: AuthProps = {
  welcome: "Login to your account",
  greeting:
    "Experience the magic of cinema with the latest blockbusters and timeless classics. Join us for unforgettable moments on the big screen!",
  actionText: "Don't have an account?",
  actionLink: "/auth/login",
  formFields: formFields,
  buttonText: "Login",
};

export default function Page() {
  return <AuthForm {...data} />;
}
