import { AuthFormProps, AuthProps } from "@/app/types/interfaces";
import Auth from "@/app/components/Auth";

const formFields: AuthFormProps[] = [
  { id: "email", type: "email", placeholder: "Email" },
  { id: "name", type: "string", placeholder: "Full Name" },
  { id: "password", type: "password", placeholder: "Password" },
];

const data: AuthProps = {
  welcome: "Create Your Account",
  greeting:
    "Embark on a journey where your aspirations meet endless possibilities. Join us today and transform your dreams into reality!",
  actionText: "Already registered?",
  actionLink: "/auth/login",
  formFields: formFields,
  buttonText: "Create account",
};

export default function Page() {
  return <Auth {...data} />;
}
