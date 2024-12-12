"use client";
import Link from "next/link";
import { useForm, SubmitHandler } from "react-hook-form";
import { AuthFormProps, AuthProps } from "@/app/types/interfaces";
import {validationRules} from "@/app/types/validationRules";

type FormValues = {
  [key: string]: string;
};

export default function AuthForm({
  welcome,
  greeting,
  actionText,
  actionLink,
  formFields,
  buttonText,
}: AuthProps) {

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormValues>({
    mode: "onBlur",
  });

  const onSubmit: SubmitHandler<Record<string, string>> = (data) => {
    console.log("Form Data:", data);
  };

  return (
    <div className="text-neutral-100 mx-auto flex flex-col justify-center items-center w-full p-10 md:w-3/5 max-w-3xl h-full mt-auto ">
      <div className="flex flex-col justify-center items-center text-center ">
        <h1 className="font-bold font-oswald text-3xl md:text-3xl lg:text-5xl">
          {welcome}
        </h1>
        <h3 className=" text-neutral-200 text-xl md:text-xl lg:text-2xl my-8">
          {greeting}
        </h3>
      </div>
      <div className="flex flex-col justify-center items-center w-full max-w-md">
        <form
          className="w-full flex flex-col gap-y-3"
          autoComplete="off"
          noValidate={true}
          onSubmit={handleSubmit(onSubmit)}
        >
          {formFields.map((field: AuthFormProps) => (
            <div key={field.id} className="flex flex-col">
              <input
                className={`w-full p-4 bg-zinc-800 box-border border-b-2 transition duration-150 ease-in-out autofill:none focus:outline-none focus:ring-0 ${
                  errors[field.id]
                    ? "border-red-500"
                    : "border-zinc-800 focus:border-orange-500"
                }`}
                id={field.id}
                type={field.type}
                placeholder={field.placeholder}
                {...register(
                  field.id,
                  validationRules[field.id as keyof typeof validationRules],
                )}
              />
              {errors[field.id] && (
                <p className="text-red-500 text-sm mt-1">
                  {errors[field.id]?.message as string}
                </p>
              )}
            </div>
          ))}
          <button
            className="w-full p-4 bg-orange-500 border-none transition duration-150 ease-in-out hover:bg-orange-600"
            type="submit"
          >
            {buttonText}
          </button>
        </form>
        <Link href={actionLink}>
          <p className="text-neutral-100 mt-6 text-md hover:cursor-pointer">
            {actionText}
          </p>
        </Link>
      </div>
    </div>
  );
}
